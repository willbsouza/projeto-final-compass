package com.compass.projetodoacao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.services.ItemService;

@RestController
@RequestMapping("/itens")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping
	public ResponseEntity<List<Item>> findAll(){
		return ResponseEntity.ok(itemService.findAll());
	}
	

}
