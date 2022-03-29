package com.compass.projetodoacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.repositories.ONGRepository;

@Service
public class ONGService {
	
	@Autowired
	private ONGRepository ongRepository;

	public List<ONG> findAll() {
		return ongRepository.findAll();
	}

	public ONG findById(Integer id) {
		//Tratar exceção dps.
		return ongRepository.findById(id).orElse(null);
	}

}
