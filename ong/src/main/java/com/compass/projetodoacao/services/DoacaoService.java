package com.compass.projetodoacao.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.client.TransporteClient;
import com.compass.projetodoacao.dto.DoacaoDTO;
import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.dto.TransporteDTO;
import com.compass.projetodoacao.entities.Doacao;
import com.compass.projetodoacao.entities.Doador;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.entities.enums.Modalidade;
import com.compass.projetodoacao.repositories.DoacaoRepository;
import com.compass.projetodoacao.repositories.DoadorRepository;
import com.compass.projetodoacao.repositories.ONGRepository;
import com.compass.projetodoacao.services.exception.HttpMessageNotReadableException;
import com.compass.projetodoacao.services.exception.InvalidAddressException;
import com.compass.projetodoacao.services.exception.InvalidQuantityException;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class DoacaoService {

	@Autowired
	private DoacaoRepository doacaoRepository;

	@Autowired
	private ONGRepository ongRepository;

	@Autowired
	private DoadorRepository doadorRepository;

	@Autowired
	private ItemService itemService;

	@Autowired
	private TransporteClient transporteClient;

	public DoacaoDTO save(@Valid DoacaoFormDTO doacaoDTO) {

		ONG ong = ongRepository.findById(doacaoDTO.getId_ong()).orElseThrow(
				() -> new ObjectNotFoundException("ONG com ID: " + doacaoDTO.getId_ong() + " não encontrado."));
		Doador doador = doadorRepository.findById(doacaoDTO.getId_doador()).orElseThrow(
				() -> new ObjectNotFoundException("Doador com ID: " + doacaoDTO.getId_doador() + " não encontrado."));
		if (doacaoDTO.getQuantidadeItem() < 1) {
			throw new InvalidQuantityException("Quantidade menor que 1.");
		}
		try {
			Item item = itemService.save(doacaoDTO);
			Doacao doacao = new Doacao();
			doacao.setItem(item);
			doacao.setDoador(doador);
			doacao.setQuantidade(doacaoDTO.getQuantidadeItem());
			doacao.setModalidade(doacaoDTO.getModalidade());
			doacao.setDataCadastro(LocalDate.now());
			doacao.setOng(ong);
			Doacao doacaoSalva = doacaoRepository.save(doacao);
			if (doacao.getModalidade() == Modalidade.DELIVERY) {
				solicitarTransporte(ong, doador, doacaoSalva);
			}
			return new DoacaoDTO(doacao);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	private TransporteDTO solicitarTransporte(ONG ong, Doador doador, Doacao doacao) {

		try {
			TransporteDTO transporteDTO = new TransporteDTO();
			transporteDTO.setEnderecoOrigem(getEndereco(doador.getEnderecos()));
			transporteDTO.setEnderecoDestino(getEndereco(ong.getEnderecos()));
			transporteDTO.setItem(doacao.getItem().getTipo().toString());
			transporteDTO.setQuantidade(doacao.getQuantidade());
			transporteDTO.setDataPedido(LocalDate.now());
			transporteDTO.setDataPrevisaoServico(LocalDate.now().plusDays(1));
			transporteDTO.setIdDoacao(doacao.getId());
			return transporteClient.solicitarTransporte(transporteDTO);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public List<DoacaoDTO> findAll() {
		List<Doacao> doacaoList = doacaoRepository.findAll();
		return doacaoList.stream().map(d -> new DoacaoDTO(d)).collect(Collectors.toList());
	}

	public DoacaoDTO findById(Integer id) {
		Doacao doacao = doacaoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return new DoacaoDTO(doacao);
	}

	public DoacaoDTO update(Integer id, @Valid DoacaoFormDTO doacaoDTO) {

		Doacao doacao = doacaoRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Doação com ID: " + id + " não encontrado."));
		ONG ong = ongRepository.findById(doacaoDTO.getId_ong()).orElseThrow(
				() -> new ObjectNotFoundException("ONG com ID: " + doacaoDTO.getId_ong() + " não encontrado."));
		Doador doador = doadorRepository.findById(doacaoDTO.getId_doador()).orElseThrow(
				() -> new ObjectNotFoundException("Doador com ID: " + doacaoDTO.getId_doador() + " não encontrado."));
		if (doacaoDTO.getQuantidadeItem() < 1) {
			throw new InvalidQuantityException("Quantidade menor que 1.");
		}
		
		if (doacao.getModalidade() == Modalidade.DELIVERY && doacaoDTO.getModalidade() == Modalidade.PRESENCIAL) {
			transporteClient.deletarTransporte(doacao.getId());
		}
		
		if (doacaoDTO.getModalidade() == Modalidade.DELIVERY) {
			solicitarTransporte(ong, doador, doacao);
		}
		try {
			Item item = itemService.atualizarItemDoacao(doacao, doacaoDTO);
			doacao.setItem(item);
			doacao.setDoador(doador);
			doacao.setQuantidade(doacaoDTO.getQuantidadeItem());
			doacao.setDataCadastro(LocalDate.now());
			doacao.setModalidade(doacaoDTO.getModalidade());
			doacao.setOng(ong);
			return new DoacaoDTO(doacao);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	public void deleteById(Integer id) {
		Doacao doacao = doacaoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		itemService.atualizaItemDeleteDoacao(doacao);
		doacaoRepository.deleteById(id);
	}
	
	private String getEndereco(List<Endereco> enderecos) {
		for (int i = 0; i < enderecos.size(); i++) {
			if (enderecos.get(i) != null) {
				return enderecos.get(i).toString();
			}
		}
		throw new InvalidAddressException("Endereço da ONG ou Doador não cadastrado.");
	}
}
