package com.compass.projetodoacao.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.projetodoacao.dto.ItemDTO;
import com.compass.projetodoacao.dto.ItemFormDTO;
import com.compass.projetodoacao.services.ItemService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/itens")
@CrossOrigin("http://localhost:4200")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@ApiOperation(value = "Retorna lista de itens cadastrados.")
	@GetMapping
	public ResponseEntity<List<ItemDTO>> findAll(){
		return new ResponseEntity<List<ItemDTO>>(itemService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna um item ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<ItemDTO> findById(@PathVariable Integer id){
		return new ResponseEntity<ItemDTO>(itemService.findById(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Atualiza as informações de um item informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ItemDTO> update(@PathVariable Integer id, @RequestBody @Valid ItemFormDTO itemFormDTO){
		return new ResponseEntity<ItemDTO>(itemService.update(id, itemFormDTO), HttpStatus.OK);
	}
}
