package com.compass.projetodoacao.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.SolicitacaoDTO;
import com.compass.projetodoacao.dto.SolicitacaoFormDTO;
import com.compass.projetodoacao.dto.SolicitacaoPutFormDTO;
import com.compass.projetodoacao.entities.Donatario;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.entities.Solicitacao;
import com.compass.projetodoacao.repositories.DonatarioRepository;
import com.compass.projetodoacao.repositories.ONGRepository;
import com.compass.projetodoacao.repositories.SolicitacaoRepository;
import com.compass.projetodoacao.services.exception.HttpMessageNotReadableException;
import com.compass.projetodoacao.services.exception.InvalidQuantityException;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class SolicitacaoService {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Autowired
	private ONGRepository ongRepository;

	@Autowired
	private DonatarioRepository donatarioRepository;
	
	@Autowired
	private ItemService itemService;

	public SolicitacaoDTO save(@Valid SolicitacaoFormDTO solicitacaoDTO) {

		ONG ong = ongRepository.findById(solicitacaoDTO.getId_ong()).orElseThrow(
				() -> new ObjectNotFoundException("ONG com ID: " + solicitacaoDTO.getId_ong() + " não encontrado."));
		Donatario donatario = donatarioRepository.findById(solicitacaoDTO.getId_donatario()).orElseThrow(
				() -> new ObjectNotFoundException("Donatário com ID: " + solicitacaoDTO.getId_donatario() + " não encontrado."));
		if (verificarQuantidadePorMesAoSolicitar(donatario, solicitacaoDTO.getQuantidadeItem()) == false) {
			throw new InvalidQuantityException("Donatário não pode realizar esta solicitação. Quantidade solicitada ultrapassa o máximo permitido por mês.");
		}
		try {
			Item item = itemService.atualizarItemPostSolicitacao(solicitacaoDTO);
			Solicitacao solicitacao = new Solicitacao();
			solicitacao.setItem(item);
			solicitacao.setOng(ong);
			solicitacao.setDonatario(donatario);
			solicitacao.setQuantidade(solicitacaoDTO.getQuantidadeItem());
			solicitacao.setDataCadastro(LocalDate.now());
			solicitacaoRepository.save(solicitacao);	
			return new SolicitacaoDTO(solicitacao);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	public List<SolicitacaoDTO> findAll() {
		List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
		return solicitacaoList.stream().map(s -> new SolicitacaoDTO(s)).collect(Collectors.toList());
	}

	public SolicitacaoDTO findById(Integer id) {
		Solicitacao solicitacao = solicitacaoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return new SolicitacaoDTO(solicitacao);
	}

	public SolicitacaoDTO update(Integer id, @Valid SolicitacaoPutFormDTO solicitacaoDTO) {
		
		Solicitacao solicitacao = solicitacaoRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Solicitação com ID: " + id + " não encontrado."));
		Donatario donatario = solicitacao.getDonatario();
		ONG ong = ongRepository.findById(solicitacaoDTO.getId_ong()).orElseThrow(
				() -> new ObjectNotFoundException("ONG com ID: " + solicitacaoDTO.getId_ong() + " não encontrado."));
		if (verificarQuantidadePorMesAoAtualizar(donatario, solicitacao, solicitacaoDTO.getQuantidadeItem()) == false) {
			throw new InvalidQuantityException("Donatário não pode realizar esta solicitação. Quantidade solicitada ultrapassa o máximo permitido por mês.");
		}
		try {
			Item item = itemService.atualizarItemPutSolicitacao(solicitacao, solicitacaoDTO);
			solicitacao.setItem(item);
			solicitacao.setOng(ong);
			solicitacao.setQuantidade(solicitacaoDTO.getQuantidadeItem());
			return new SolicitacaoDTO(solicitacao);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	public void deleteById(Integer id) {
		
		Solicitacao solicitacao = solicitacaoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		itemService.atualizaItemDeleteSolicitacao(solicitacao);
		solicitacaoRepository.deleteById(id);
	}
	
	private Boolean verificarQuantidadePorMesAoSolicitar(Donatario donatario, Integer quantidadeSolicitacaoAtual) {
		
		Integer quantidadeSolicitadaUltimos30Dias = donatario.getSolicitacoes()
				.stream()
				.filter(s -> ChronoUnit.DAYS.between(s.getDataCadastro(), LocalDate.now()) < 30)
				.mapToInt(s -> s.getQuantidade())
				.sum();
		if (quantidadeSolicitadaUltimos30Dias + quantidadeSolicitacaoAtual > 3) {
			return false;
		} else {
			return true;
		}
	}
	
	private Boolean verificarQuantidadePorMesAoAtualizar(Donatario donatario, Solicitacao solicitacao, Integer quantidadeSolicitacaoAtual) {	
		Integer quantidadeSolicitadaUltimos30Dias = donatario.getSolicitacoes()
				.stream()
				.filter(s -> ChronoUnit.DAYS.between(s.getDataCadastro(), LocalDate.now()) < 30)
				.mapToInt(s -> s.getQuantidade())
				.sum();
		if (quantidadeSolicitadaUltimos30Dias + quantidadeSolicitacaoAtual - solicitacao.getQuantidade() > 3) {
			return false;
		} else {
			return true;
		}
	}
}
