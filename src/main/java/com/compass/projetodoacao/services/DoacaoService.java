package com.compass.projetodoacao.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.entities.Categoria;
import com.compass.projetodoacao.entities.Doacao;
import com.compass.projetodoacao.entities.Doador;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.entities.enums.Status;
import com.compass.projetodoacao.repositories.CategoriaRepository;
import com.compass.projetodoacao.repositories.DoacaoRepository;
import com.compass.projetodoacao.repositories.DoadorRepository;
import com.compass.projetodoacao.repositories.ItemRepository;
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
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	public Doacao save(DoacaoFormDTO doacaoDTO) {
		
		ONG ong = ongRepository.findById(doacaoDTO.getId_ong()).orElse(null); //Tratar exceção
		Doador doador = doadorRepository.findById(doacaoDTO.getId_doador()).orElse(null); //Tratar exceção
		Categoria categoria = categoriaRepository.findById(doacaoDTO.getId_categoria()).orElse(null); //Tratar exceção
		
		Doacao doacao = new Doacao();
		doacao.setDataCadastro(LocalDate.now());
		doacao.setStatus(Status.ABERTA);
		doacao.setOng(ong);
		doacao.setDoador(doador);
		
		Item item = new Item();
		item.setNome(doacaoDTO.getNomeItem());
		item.setQuantidade(doacaoDTO.getQuantidade());
		item.setCategoria(categoria);
		item.setDoacao(doacao);
		itemRepository.save(item);
		
		return doacaoRepository.save(doacao);
	}
	
	public List<Doacao> findAll(){
		return doacaoRepository.findAll();
	}
}
