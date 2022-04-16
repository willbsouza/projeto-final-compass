package com.compass.transportador.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.transportador.entity.Transporte;
import com.compass.transportador.repository.TransporteRepository;
import com.compass.transportador.service.exception.HttpMessageNotReadableException;
import com.compass.transportador.service.exception.MethodArgumentNotValidException;

@Service
public class TransporteService {

	@Autowired
	private TransporteRepository transporteRepository;

	public Transporte save(@Valid Transporte transporte) {
		try {
			return transporteRepository.save(transporte);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}

	public List<Transporte> findAll() {
		return transporteRepository.findAll();
	}

	public Transporte findByIdDoacao(Integer idDoacao) {
		return transporteRepository.findByIdDoacao(idDoacao);
	}

	public void deleteByIdDoacao(Integer idDoacao) {
		transporteRepository.deleteByIdDoacao(idDoacao);
	}

	public Transporte update(Integer idDoacao, @Valid Transporte transporte) {
		Transporte obj = transporteRepository.findByIdDoacao(idDoacao);
		try {
			obj.setDataPedido(transporte.getDataPedido());
			obj.setDataPrevisaoServico(transporte.getDataPrevisaoServico());
			obj.setEnderecoDestino(transporte.getEnderecoDestino());
			obj.setEnderecoOrigem(transporte.getEnderecoOrigem());
			obj.setIdDoacao(transporte.getIdDoacao());
			obj.setItem(transporte.getItem());
			obj.setQuantidade(transporte.getQuantidade());
			return obj;
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		} catch (HttpMessageNotReadableException e) {
			throw new HttpMessageNotReadableException(e.getMessage());
		}
	}
}
