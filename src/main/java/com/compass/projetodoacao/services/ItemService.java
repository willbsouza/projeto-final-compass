package com.compass.projetodoacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.entities.Categoria;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.repositories.CategoriaRepository;
import com.compass.projetodoacao.repositories.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	@Transactional
	public Item save(DoacaoFormDTO doacaoDTO) {
		
		Categoria categoria = categoriaRepository.findById(doacaoDTO.getId_categoria()).orElse(null); //Tratar exceção
		Item item = itemRepository.findByTipo(doacaoDTO.getTipoItem());
		if(item != null) {
			item.setQuantidade(item.getQuantidade() + doacaoDTO.getQuantidade());
			return item;
		} else {
			Item itemNovo = new Item();
			itemNovo.setQuantidade(doacaoDTO.getQuantidade());
			itemNovo.setTipo(doacaoDTO.getTipoItem());
			itemNovo.setCategoria(categoria);
			return itemRepository.save(itemNovo);
		}
	}

	public List<Item> findAll() {
		return itemRepository.findAll();
	}
}
