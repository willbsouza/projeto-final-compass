package com.compass.projetodoacao.services;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.entities.Doacao;
import com.compass.projetodoacao.entities.Doador;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.repositories.DoacaoRepository;
import com.compass.projetodoacao.repositories.DoadorRepository;
import com.compass.projetodoacao.repositories.ONGRepository;
import com.compass.projetodoacao.services.exception.HttpMessageNotReadableException;
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

	public Doacao save(@Valid DoacaoFormDTO doacaoDTO) {

		ONG ong = ongRepository.findById(doacaoDTO.getId_ong()).orElseThrow(
				() -> new ObjectNotFoundException("ONG com ID: " + doacaoDTO.getId_ong() + " não encontrado."));
		Doador doador = doadorRepository.findById(doacaoDTO.getId_doador()).orElseThrow(
				() -> new ObjectNotFoundException("ONG com ID: " + doacaoDTO.getId_doador() + " não encontrado."));
		try {
			Item item = itemService.save(doacaoDTO);
			Doacao doacao = new Doacao();
			doacao.setItem(item);
			doacao.setDataCadastro(LocalDate.now());
			doacao.setOng(ong);
			doacao.setDoador(doador);
			return doacaoRepository.save(doacao);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	public List<Doacao> findAll() {
		return doacaoRepository.findAll();
	}

}
