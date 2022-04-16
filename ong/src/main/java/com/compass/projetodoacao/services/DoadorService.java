package com.compass.projetodoacao.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoadorDTO;
import com.compass.projetodoacao.dto.DoadorFormDTO;
import com.compass.projetodoacao.dto.DoadorPostFormDTO;
import com.compass.projetodoacao.entities.Doador;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.repositories.DoadorRepository;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class DoadorService {

	@Autowired
	private DoadorRepository doadorRepository;
	
	public List<DoadorDTO> findAll() {		
		List<Doador> doadorList = doadorRepository.findAll();
		return doadorList.stream().map(d -> new DoadorDTO(d)).collect(Collectors.toList());
	}

	public DoadorDTO findById(Integer id) {

		Doador doador = doadorRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return new DoadorDTO(doador);
	}

	public DoadorDTO save(DoadorPostFormDTO doadorDTO) {
		
		Telefone telefone = new Telefone(doadorDTO.getTelefone());
		Endereco endereco = new Endereco(doadorDTO);
		try {
			Doador doador = new Doador();
			doador.adicionarEndereco(endereco);
			doador.adicionarTelefone(telefone);
			doador.setCpf(doadorDTO.getCpfDoador());
			doador.setNome(doadorDTO.getNomeDoador());
			doador.setSenha(doadorDTO.getSenha());
			doadorRepository.save(doador);
			return new DoadorDTO(doador);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public DoadorDTO update(Integer id, @Valid DoadorFormDTO doadorDTO) {
		Doador doador = doadorRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		try {
			doador.setCpf(doadorDTO.getCpfDoador());
			doador.setNome(doadorDTO.getNomeDoador());
			return new DoadorDTO(doador);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public void deleteById(Integer id) {
		findById(id);
		doadorRepository.deleteById(id);		
	}
}
