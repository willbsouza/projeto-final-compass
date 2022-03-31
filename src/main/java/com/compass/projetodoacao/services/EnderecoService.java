package com.compass.projetodoacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoadorFormDTO;
import com.compass.projetodoacao.dto.ONGFormDTO;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco saveEnderecoDoador(DoadorFormDTO doadorDTO) {
		Endereco endereco = new Endereco();
		endereco.setLogradouro(doadorDTO.getLogradouro());
		endereco.setNumero(doadorDTO.getNumero());
		endereco.setComplemento(doadorDTO.getComplemento());
		endereco.setBairro(doadorDTO.getBairro());
		endereco.setCidade(doadorDTO.getCidade());
		endereco.setEstado(doadorDTO.getEstado());
		endereco.setCep(doadorDTO.getCep());
		return enderecoRepository.save(endereco);
	}

	public Endereco saveEnderecoONG(ONGFormDTO ongDTO) {
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
}
