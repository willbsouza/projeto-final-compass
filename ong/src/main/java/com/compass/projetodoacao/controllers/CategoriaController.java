package com.compass.projetodoacao.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.projetodoacao.entities.Categoria;
import com.compass.projetodoacao.services.CategoriaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("http://localhost:4200")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@ApiOperation(value = "Retorna lista de categorias cadastradas.")
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll(){
		return new ResponseEntity<List<Categoria>>(categoriaService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna uma categoria ao informar ID existente.")
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id){
		return new ResponseEntity<Categoria>(categoriaService.findById(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Cadastra uma categoria")
	@PostMapping
	@Transactional
	public ResponseEntity<Categoria> save(@RequestBody @Valid Categoria categoria){
		return new ResponseEntity<Categoria>(categoriaService.save(categoria), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Atualiza as informações de uma categoria informando um ID existente.")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Categoria> save(@PathVariable Integer id, @RequestBody @Valid Categoria categoria){
		return new ResponseEntity<Categoria>(categoriaService.update(id, categoria), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Exclui uma categoria ao informar um ID existente.")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		categoriaService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
