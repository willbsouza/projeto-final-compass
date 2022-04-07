package com.compass.transportador.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.transportador.entity.Transporte;
import com.compass.transportador.repository.TransporteRepository;

@Service
public class TransporteService {

	@Autowired
	private TransporteRepository transporteRepository;

	public Transporte save(@Valid Transporte transporte) {
		return transporteRepository.save(transporte);
	}

	public List<Transporte> findAll() {
		return transporteRepository.findAll();
	}
}
