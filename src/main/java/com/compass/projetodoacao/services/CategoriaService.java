package com.compass.projetodoacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.entities.Categoria;
import com.compass.projetodoacao.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria findById(Integer id) {
		//tratar exceção
		return categoriaRepository.findById(id).orElse(null);
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update(Integer id, Categoria categoria) {
		Categoria obj = findById(id);
		obj.getNome();
		return obj;
	}
	
	public void deleteById(Integer id) {
		findById(id);
		categoriaRepository.deleteById(id);
	}
}
