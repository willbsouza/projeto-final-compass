package com.compass.projetodoacao.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.compass.projetodoacao.dto.DoacaoFormDTO;
import com.compass.projetodoacao.dto.ItemDTO;
import com.compass.projetodoacao.entities.Categoria;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.repositories.CategoriaRepository;
import com.compass.projetodoacao.repositories.ItemRepository;
import com.compass.projetodoacao.services.exception.HttpMessageNotReadableException;
import com.compass.projetodoacao.services.exception.InvalidQuantityException;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	@Transactional
	public Item save(@Valid DoacaoFormDTO doacaoDTO) {

		Categoria categoria = categoriaRepository.findById(doacaoDTO.getId_categoria()).orElseThrow(
				() -> new ObjectNotFoundException("ID: " + doacaoDTO.getId_categoria() + " não encontrado."));
		Item item = itemRepository.findByTipo(doacaoDTO.getTipoItem());
		try {
			if(doacaoDTO.getQuantidadeItem() < 1) {
				throw new InvalidQuantityException("Quantidade menor que 1.");
			}
			if (item != null) {
				item.setQuantidadeTotal(item.getQuantidadeTotal() + doacaoDTO.getQuantidadeItem());
				return item;
			} else {
				Item itemNovo = new Item();
				itemNovo.setQuantidadeTotal(doacaoDTO.getQuantidadeItem());
				itemNovo.setTipo(doacaoDTO.getTipoItem());
				itemNovo.setCategoria(categoria);
				itemRepository.save(itemNovo);
				return itemNovo;
			}
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	private ItemDTO converter(Item item) {
		return new ItemDTO(item);
	}

	public List<ItemDTO> findAll() {
		List<Item> listItem = itemRepository.findAll();
		return listItem.stream().map(i -> converter(i)).collect(Collectors.toList());
	}

	public ItemDTO findById(Integer id) {
		Item item = itemRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return converter(item);	
	}
	
	public ItemDTO update(Integer id, @Valid Item item) {
		Item obj = itemRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		if(item.getQuantidadeTotal() < 1) {
			throw new InvalidQuantityException("Quantidade menor que 1.");
		}
		if (obj != null) {
			obj.setQuantidadeTotal(item.getQuantidadeTotal());
			obj.setTipo(item.getTipo());
		}
		return converter(obj);
	}

	public void deleteById(Integer id) {
		findById(id);
		itemRepository.deleteById(id);
	}
}
