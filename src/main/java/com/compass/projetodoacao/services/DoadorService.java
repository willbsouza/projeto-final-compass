package com.compass.projetodoacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoadorFormDTO;
import com.compass.projetodoacao.entities.Doador;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.repositories.DoadorRepository;

@Service
public class DoadorService {
	
	@Autowired
	private DoadorRepository doadorRepository;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@Autowired
	private EnderecoService enderecoService;

	public Doador save(DoadorFormDTO doadorDTO) {
		
		Endereco endereco = enderecoService.saveEnderecoDoador(doadorDTO);
		Telefone telefone = telefoneService.saveTelefoneDoador(doadorDTO);
		
		Doador doador = new Doador();
		doador.adicionarEndereco(endereco);
		doador.adicionarTelefone(telefone);
		doador.setCpf(doadorDTO.getCpfDoador());
		doador.setNome(doadorDTO.getNomeDoador());
		return doadorRepository.save(doador);
	}

	public List<Doador> findAll() {
		return doadorRepository.findAll();
	}

	public Doador findById(Integer id) {
		//tratar exceção
		return doadorRepository.findById(id).orElse(null);
	}
}
