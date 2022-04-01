package com.compass.projetodoacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.DoadorFormDTO;
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

	@Autowired
	private TelefoneService telefoneService;

	@Autowired
	private EnderecoService enderecoService;

	public Doador save(DoadorFormDTO doadorDTO) {

		Endereco endereco = enderecoService.saveEnderecoDoador(doadorDTO);
		Telefone telefone = telefoneService.saveTelefoneDoador(doadorDTO);
		try {
			Doador doador = new Doador();
			doador.adicionarEndereco(endereco);
			doador.adicionarTelefone(telefone);
			doador.setCpf(doadorDTO.getCpfDoador());
			doador.setNome(doadorDTO.getNomeDoador());
			return doadorRepository.save(doador);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public List<Doador> findAll() {
		return doadorRepository.findAll();
	}

	public Doador findById(Integer id) {

		return doadorRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
	}
}
