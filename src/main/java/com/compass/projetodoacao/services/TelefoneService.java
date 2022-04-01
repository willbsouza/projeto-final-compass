package com.compass.projetodoacao.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoadorFormDTO;
import com.compass.projetodoacao.dto.ONGFormDTO;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.repositories.TelefoneRepository;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public List<Telefone> findAll() {
		return telefoneRepository.findAll();
	}
	
	public Telefone findById(Integer id) {
		return telefoneRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
	}

	public Telefone saveTelefoneDoador(@Valid DoadorFormDTO doadorDTO) {
		try {
			Telefone telefone = new Telefone();
			telefone.setNumero(doadorDTO.getTelefone());
			return telefoneRepository.save(telefone);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public Telefone saveTelefoneONG(@Valid ONGFormDTO ongDTO) {
		try {
			Telefone telefone = new Telefone();
			telefone.setNumero(ongDTO.getTelefone());
			return telefoneRepository.save(telefone);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public Telefone update(Integer id, @Valid Telefone telefone) {
		Telefone obj = findById(id);
		obj.setNumero(telefone.getNumero());
		return obj;
	}

	public void deleteById(Integer id) {
		findById(id);
		telefoneRepository.deleteById(id);
	}
}
;