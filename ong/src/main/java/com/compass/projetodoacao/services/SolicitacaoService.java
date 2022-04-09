package com.compass.projetodoacao.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.SolicitacaoDTO;
import com.compass.projetodoacao.dto.SolicitacaoFormDTO;
import com.compass.projetodoacao.entities.Donatario;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.entities.Solicitacao;
import com.compass.projetodoacao.repositories.DonatarioRepository;
import com.compass.projetodoacao.repositories.ONGRepository;
import com.compass.projetodoacao.repositories.SolicitacaoRepository;
import com.compass.projetodoacao.services.exception.HttpMessageNotReadableException;
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
				() -> new ObjectNotFoundException("ONG com ID: " + solicitacaoDTO.getId_donatario() + " não encontrado."));
		try {
			Item item = itemService.atualizarItemPostSolicitacao(solicitacaoDTO);
			Solicitacao solicitacao = new Solicitacao();
			solicitacao.setItem(item);
			solicitacao.setOng(ong);
			solicitacao.setDonatario(donatario);
			solicitacao.setQuantidade(solicitacaoDTO.getQuantidadeItem());
			solicitacao.setDataCadastro(LocalDate.now());
			solicitacaoRepository.save(solicitacao);
			return converter(solicitacao);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	public List<SolicitacaoDTO> findAll() {
		List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
		return solicitacaoList.stream().map(s -> converter(s)).collect(Collectors.toList());
	}

	public SolicitacaoDTO findById(Integer id) {
		Solicitacao solicitacao = solicitacaoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return converter(solicitacao);
	}

	public SolicitacaoDTO update(Integer id, @Valid SolicitacaoFormDTO solicitacaoDTO) {
		
		Solicitacao solicitacao = solicitacaoRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Solicitação com ID: " + solicitacaoDTO.getId_ong() + " não encontrado."));
		ONG ong = ongRepository.findById(solicitacaoDTO.getId_ong()).orElseThrow(
				() -> new ObjectNotFoundException("ONG com ID: " + solicitacaoDTO.getId_ong() + " não encontrado."));
		Donatario donatario = donatarioRepository.findById(solicitacaoDTO.getId_donatario()).orElseThrow(
				() -> new ObjectNotFoundException("Doador com ID: " + solicitacaoDTO.getId_donatario() + " não encontrado."));
		try {
			Item item = itemService.atualizarItemPutSolicitacao(solicitacao, solicitacaoDTO);
			solicitacao.setItem(item);
			solicitacao.setOng(ong);
			solicitacao.setDonatario(donatario);
			solicitacao.setQuantidade(solicitacaoDTO.getQuantidadeItem());
			return converter(solicitacao);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	public void deleteById(Integer id) {
		findById(id);
		solicitacaoRepository.deleteById(id);
	}

	private SolicitacaoDTO converter(Solicitacao solicitacao) {
		return new SolicitacaoDTO(solicitacao);
	}
}
