package com.compass.projetodoacao.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoacaoDTO;
import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.entities.Doacao;
import com.compass.projetodoacao.entities.Doador;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.repositories.DoacaoRepository;
import com.compass.projetodoacao.repositories.DoadorRepository;
import com.compass.projetodoacao.repositories.ONGRepository;
import com.compass.projetodoacao.services.exception.HttpMessageNotReadableException;
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

	public DoacaoDTO save(@Valid DoacaoFormDTO doacaoDTO) {

		ONG ong = ongRepository.findById(doacaoDTO.getId_ong()).orElseThrow(
				() -> new ObjectNotFoundException("ONG com ID: " + doacaoDTO.getId_ong() + " não encontrado."));
		Doador doador = doadorRepository.findById(doacaoDTO.getId_doador()).orElseThrow(
				() -> new ObjectNotFoundException("ONG com ID: " + doacaoDTO.getId_doador() + " não encontrado."));
		if (doacaoDTO.getQuantidadeItem() < 1) {
			throw new InvalidQuantityException("Quantidade menor que 1.");
		}
		try {
			Item item = itemService.save(doacaoDTO);
			Doacao doacao = new Doacao();
			doacao.setItem(item);
			doacao.setQuantidade(doacaoDTO.getQuantidadeItem());
			doacao.setDataCadastro(LocalDate.now());
			doacao.setOng(ong);
			doacao.setDoador(doador);
			doacaoRepository.save(doacao);
			return converter(doacao);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	public List<DoacaoDTO> findAll() {
		List<Doacao> doacaoList = doacaoRepository.findAll();
		return doacaoList.stream().map(d -> converter(d)).collect(Collectors.toList());
	}

	public DoacaoDTO findById(Integer id) {
		Doacao doacao = doacaoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return converter(doacao);
	}

	public DoacaoDTO update(Integer id, @Valid DoacaoFormDTO doacaoDTO) {
		
		Doacao doacao = doacaoRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Doação com ID: " + doacaoDTO.getId_ong() + " não encontrado."));
		ONG ong = ongRepository.findById(doacaoDTO.getId_ong()).orElseThrow(
				() -> new ObjectNotFoundException("ONG com ID: " + doacaoDTO.getId_ong() + " não encontrado."));
		Doador doador = doadorRepository.findById(doacaoDTO.getId_doador()).orElseThrow(
				() -> new ObjectNotFoundException("Doador com ID: " + doacaoDTO.getId_doador() + " não encontrado."));
		if (doacaoDTO.getQuantidadeItem() < 1) {
			throw new InvalidQuantityException("Quantidade menor que 1.");
		}
		try {
			Item item = itemService.save(doacaoDTO);
			doacao.setItem(item);
			doacao.setQuantidade(doacaoDTO.getQuantidadeItem());
			doacao.setDataCadastro(LocalDate.now());
			doacao.setOng(ong);
			doacao.setDoador(doador);
			return converter(doacao);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	public void deleteById(Integer id) {
		findById(id);
		doadorRepository.deleteById(id);
	}

	private DoacaoDTO converter(Doacao doacao) {
		return new DoacaoDTO(doacao);
	}
}
