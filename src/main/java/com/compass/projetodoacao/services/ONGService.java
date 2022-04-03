package com.compass.projetodoacao.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.projetodoacao.dto.ONGDTO;
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

	public List<ONGDTO> findAll() {
		List<ONG> listONG = ongRepository.findAll();
		return listONG.stream().map(o -> converter(o)).collect(Collectors.toList());
	}

	public ONGDTO findById(Integer id) {
		ONG ong = ongRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		return converter(ong);
	}

	public ONGDTO save(@Valid ONGFormDTO ongDTO) {

		Endereco endereco = enderecoService.saveEnderecoONG(ongDTO);
		Telefone telefone = telefoneService.saveTelefoneONG(ongDTO);
		try {
			ONG ong = new ONG();
			ong.adicionarEndereco(endereco);
			ong.adicionarTelefone(telefone);
			ong.setFilial(ongDTO.getFilialONG());
			ongRepository.save(ong);
			return converter(ong);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public ONGDTO update(Integer id, @Valid ONGFormDTO ongDTO) {
		ONG ong = ongRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID: " + id + " não encontrado."));
		Endereco endereco = enderecoService.saveEnderecoONG(ongDTO);
		Telefone telefone = telefoneService.saveTelefoneONG(ongDTO);
		try {
			ong.adicionarEndereco(endereco);
			ong.adicionarTelefone(telefone);
			ong.setFilial(ongDTO.getFilialONG());
			return converter(ong);
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}

	public void deleteById(Integer id) {
		findById(id);
		ongRepository.deleteById(id);		
	}

	private ONGDTO converter(ONG ong) {
		return new ONGDTO(ong);
	}
}
