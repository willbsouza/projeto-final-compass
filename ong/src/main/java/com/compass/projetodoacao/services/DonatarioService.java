package com.compass.projetodoacao.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DonatarioDTO;
import com.compass.projetodoacao.dto.DonatarioFormDTO;
import com.compass.projetodoacao.dto.DonatarioPostFormDTO;
import com.compass.projetodoacao.dto.EnderecoFormDTO;
import com.compass.projetodoacao.dto.TelefoneFormDTO;
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

	@Autowired
	private TelefoneService telefoneService;

	@Autowired
	private EnderecoService enderecoService;

	public DonatarioDTO save(DonatarioPostFormDTO donatarioDTO) {
		
		Endereco endereco = enderecoService.saveEndereco(new EnderecoFormDTO(donatarioDTO));
		Telefone telefone = telefoneService.saveTelefone(new TelefoneFormDTO(donatarioDTO));
		try {
			Donatario donatario = new Donatario();
			donatario.adicionarEndereco(endereco);
			donatario.adicionarTelefone(telefone);
			donatario.setCpf(donatarioDTO.getCpfDonatario());
			donatario.setNome(donatarioDTO.getNomeDonatario());
			donatarioRepository.save(donatario);
			return converter(donatario);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public List<DonatarioDTO> findAll() {		
		List<Donatario> donatarioList = donatarioRepository.findAll();
		return donatarioList.stream().map(d -> converter(d)).collect(Collectors.toList());
	}

	public DonatarioDTO findById(Integer id) {

		Donatario donatario = donatarioRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return converter(donatario);
	}
	
	public void deleteById(Integer id) {
		findById(id);
		donatarioRepository.deleteById(id);		
	}

	public DonatarioDTO update(Integer id, @Valid DonatarioFormDTO donatarioDTO) {
		Donatario donatario = donatarioRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		try {
			donatario.setCpf(donatarioDTO.getCpfDonatario());
			donatario.setNome(donatarioDTO.getNomeDonatario());
			return converter(donatario);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	private DonatarioDTO converter(Donatario donatario) {
		return new DonatarioDTO(donatario);
	}
}
