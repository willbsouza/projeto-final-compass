package com.compass.transportador.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.transportador.entity.Transporte;
import com.compass.transportador.repository.TransporteRepository;
import com.compass.transportador.service.exception.ObjectNotFoundException;

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
	
	public Transporte findById(Integer id) {
		return transporteRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Transporte com ID: " + id + " não encontrado."));
	}

	public void deleteByIdDoacao(Integer idDoacao) {
		transporteRepository.findById(idDoacao).orElseThrow(
				() -> new ObjectNotFoundException("Doação Id: " + idDoacao + " não encontrado."));
		transporteRepository.deleteByIdDoacao(idDoacao);
	}

	public Transporte update(Integer id, @Valid Transporte transporte) {
		Transporte obj =  transporteRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Transporte com ID: " + id + " não encontrado."));
		obj.setDataPedido(transporte.getDataPedido());
		obj.setDataPrevisaoServico(transporte.getDataPrevisaoServico());
		obj.setEnderecoDestino(transporte.getEnderecoDestino());
		obj.setEnderecoOrigem(transporte.getEnderecoOrigem());
		obj.setIdDoacao(transporte.getIdDoacao());
		obj.setItem(transporte.getItem());
		obj.setQuantidade(transporte.getQuantidade());
		return obj;
	}
}
