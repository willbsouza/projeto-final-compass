package com.compass.projetodoacao.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.entities.Categoria;
import com.compass.projetodoacao.repositories.CategoriaRepository;
import com.compass.projetodoacao.services.exception.DataIntegrityViolationException;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria findById(Integer id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
	}

	public Categoria save(@Valid Categoria categoria) {
		try {
			return categoriaRepository.save(categoria);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public Categoria update(@Valid Integer id, Categoria categoria) {
		try {
			Categoria obj = findById(id);
			obj.setNome(categoria.getNome());
			return obj;
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public void deleteById(Integer id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(e.getMessage());
		}
	}
}
