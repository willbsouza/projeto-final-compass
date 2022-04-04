package com.compass.projetodoacao.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.EnderecoFormDTO;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.repositories.EnderecoRepository;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco saveEndereco(@Valid EnderecoFormDTO enderecoDTO) {
		try {
			Endereco endereco = new Endereco();
			endereco.setLogradouro(enderecoDTO.getLogradouro());
			endereco.setNumero(enderecoDTO.getNumero());
			endereco.setComplemento(enderecoDTO.getComplemento());
			endereco.setBairro(enderecoDTO.getBairro());
			endereco.setCidade(enderecoDTO.getCidade());
			endereco.setEstado(enderecoDTO.getEstado());
			endereco.setCep(enderecoDTO.getCep());
			return enderecoRepository.save(endereco);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}

	public Endereco findById(Integer id) {
		return enderecoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
	}

	public Endereco update(@Valid Integer id, EnderecoFormDTO body) {
		Endereco endereco = enderecoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Endereco não encontrado. Id invalido"));
		endereco.setLogradouro(body.getLogradouro());
		endereco.setBairro(body.getBairro());
		endereco.setNumero(body.getNumero());
		endereco.setCidade(body.getCidade());
		endereco.setEstado(body.getEstado());
		endereco.setCep(body.getCep());
		endereco.setComplemento(body.getComplemento());
		return endereco;
	}
	
	public void deleteById(Integer id) {
		findById(id);
		enderecoRepository.deleteById(id);
	}

}
