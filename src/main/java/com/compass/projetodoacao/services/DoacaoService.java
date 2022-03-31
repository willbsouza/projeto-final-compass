package com.compass.projetodoacao.services;

import java.time.LocalDate;
import java.util.List;

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

	public Doacao save(DoacaoFormDTO doacaoDTO) {
		
		ONG ong = ongRepository.findById(doacaoDTO.getId_ong()).orElse(null); //Tratar exceção
		Doador doador = doadorRepository.findById(doacaoDTO.getId_doador()).orElse(null); //Tratar exceção
		Item item = itemService.save(doacaoDTO);
		Doacao doacao = new Doacao();
		doacao.setItem(item);
		doacao.setDataCadastro(LocalDate.now());
		doacao.setOng(ong);
		doacao.setDoador(doador);
		
		return doacaoRepository.save(doacao);
	}
	
	public List<Doacao> findAll(){
		return doacaoRepository.findAll();
	}
	
}
