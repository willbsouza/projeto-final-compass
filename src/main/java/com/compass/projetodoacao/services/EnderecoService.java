package com.compass.projetodoacao.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoadorFormDTO;
import com.compass.projetodoacao.dto.ONGFormDTO;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.repositories.EnderecoRepository;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco saveEnderecoDoador(@Valid DoadorFormDTO doadorDTO) {
		try {
			Endereco endereco = new Endereco();
			endereco.setLogradouro(doadorDTO.getLogradouro());
			endereco.setNumero(doadorDTO.getNumero());
			endereco.setComplemento(doadorDTO.getComplemento());
			endereco.setBairro(doadorDTO.getBairro());
			endereco.setCidade(doadorDTO.getCidade());
			endereco.setEstado(doadorDTO.getEstado());
			endereco.setCep(doadorDTO.getCep());
			return enderecoRepository.save(endereco);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public Endereco saveEnderecoONG(@Valid ONGFormDTO ongDTO) {
		Endereco endereco = new Endereco();
		endereco.setLogradouro(ongDTO.getLogradouro());
		endereco.setNumero(ongDTO.getNumero());
		endereco.setComplemento(ongDTO.getComplemento());
		endereco.setBairro(ongDTO.getBairro());
		endereco.setCidade(ongDTO.getCidade());
		endereco.setEstado(ongDTO.getEstado());
		endereco.setCep(ongDTO.getCep());
		return enderecoRepository.save(endereco);
	}
	
	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}

	public Endereco findById(Integer id) {
		return enderecoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
	}

	
	}

