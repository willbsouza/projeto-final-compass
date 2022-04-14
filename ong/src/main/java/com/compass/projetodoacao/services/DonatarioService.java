package com.compass.projetodoacao.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DonatarioDTO;
import com.compass.projetodoacao.dto.DonatarioFormDTO;
import com.compass.projetodoacao.dto.DonatarioPostFormDTO;
import com.compass.projetodoacao.entities.Donatario;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.repositories.DonatarioRepository;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class DonatarioService {

	@Autowired
	private DonatarioRepository donatarioRepository;

	public DonatarioDTO save(DonatarioPostFormDTO donatarioDTO) {
		
		Endereco endereco = new Endereco(donatarioDTO);
		Telefone telefone = new Telefone(donatarioDTO.getTelefone());
		try {
			Donatario donatario = new Donatario();
			donatario.adicionarEndereco(endereco);
			donatario.adicionarTelefone(telefone);
			donatario.setCpf(donatarioDTO.getCpfDonatario());
			donatario.setNome(donatarioDTO.getNomeDonatario());
			donatarioRepository.save(donatario);
			return new DonatarioDTO(donatario);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public List<DonatarioDTO> findAll() {		
		List<Donatario> donatarioList = donatarioRepository.findAll();
		return donatarioList.stream().map(d -> new DonatarioDTO(d)).collect(Collectors.toList());
	}

	public DonatarioDTO findById(Integer id) {

		Donatario donatario = donatarioRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return new DonatarioDTO(donatario);
	}
	
	public DonatarioDTO update(Integer id, @Valid DonatarioFormDTO donatarioDTO) {
		Donatario donatario = donatarioRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		try {
			donatario.setCpf(donatarioDTO.getCpfDonatario());
			donatario.setNome(donatarioDTO.getNomeDonatario());
			return new DonatarioDTO(donatario);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public void deleteById(Integer id) {
		findById(id);
		donatarioRepository.deleteById(id);	
	}
}
