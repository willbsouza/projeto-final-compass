package com.compass.projetodoacao.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.ONGDTO;
import com.compass.projetodoacao.dto.ONGFormDTO;
import com.compass.projetodoacao.dto.ONGPostFormDTO;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.repositories.ONGRepository;
import com.compass.projetodoacao.services.exception.DataIntegrityViolationException;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class ONGService {

	@Autowired
	private ONGRepository ongRepository;

	public List<ONGDTO> findAll() {
		List<ONG> listONG = ongRepository.findAll();
		return listONG.stream().map(ong -> new ONGDTO(ong)).collect(Collectors.toList());
	}

	public ONGDTO findById(Integer id) {
		ONG ong = ongRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return new ONGDTO(ong);
	}

	public ONGDTO save(@Valid ONGPostFormDTO ongDTO) {

		Endereco endereco = new Endereco(ongDTO);
		Telefone telefone = new Telefone(ongDTO.getTelefone());
		try {
			ONG ong = new ONG();
			ong.adicionarEndereco(endereco);
			ong.adicionarTelefone(telefone);
			ong.setFilial(ongDTO.getFilialONG());
			ong.setCnpj(ongDTO.getCnpj());
			ong.setSenha(ongDTO.getSenha());
			ongRepository.save(ong);
			return new ONGDTO(ong);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public ONGDTO update(Integer id, @Valid ONGFormDTO ongDTO) {
		ONG ong = ongRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		try {
			ong.setFilial(ongDTO.getFilialONG());
			ong.setCnpj(ongDTO.getCnpj());
			return new ONGDTO(ong);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public void deleteById(Integer id) {
		findById(id);
		try {
			ongRepository.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(e.getMessage());
		}
	}
}
