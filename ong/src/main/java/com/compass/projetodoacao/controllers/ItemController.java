package com.compass.projetodoacao.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.projetodoacao.dto.ItemDTO;
import com.compass.projetodoacao.dto.ItemFormDTO;
import com.compass.projetodoacao.services.ItemService;

@RestController
@RequestMapping("/itens")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping
	public ResponseEntity<List<ItemDTO>> findAll(){
		return ResponseEntity.ok(itemService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemDTO> findById(@PathVariable Integer id){
		return ResponseEntity.ok(itemService.findById(id));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ItemDTO> update(@PathVariable Integer id, @RequestBody @Valid ItemFormDTO itemFormDTO){
		return ResponseEntity.ok(itemService.update(id, itemFormDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		itemService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
