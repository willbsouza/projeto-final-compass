package com.compass.projetodoacao.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.ONGFormDTO;
import com.compass.projetodoacao.entities.Endereco;
import com.compass.projetodoacao.entities.ONG;
import com.compass.projetodoacao.entities.Telefone;
import com.compass.projetodoacao.repositories.ONGRepository;
import com.compass.projetodoacao.services.exception.MethodArgumentNotValidException;
import com.compass.projetodoacao.services.exception.ObjectNotFoundException;

@Service
public class ONGService {

	@Autowired
	private ONGRepository ongRepository;

	@Autowired
	private TelefoneService telefoneService;

	@Autowired
	private EnderecoService enderecoService;

	public List<ONG> findAll() {
		return ongRepository.findAll();
	}

	public ONG findById(Integer id) {
		return ongRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
	}

	public ONG save(@Valid ONGFormDTO ongDTO) {

		Endereco endereco = enderecoService.saveEnderecoONG(ongDTO);
		Telefone telefone = telefoneService.saveTelefoneONG(ongDTO);
		try {
			ONG ong = new ONG();
			ong.adicionarEndereco(endereco);
			ong.adicionarTelefone(telefone);
			ong.setFilial(ongDTO.getFilialONG());
			return ongRepository.save(ong);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}
}
