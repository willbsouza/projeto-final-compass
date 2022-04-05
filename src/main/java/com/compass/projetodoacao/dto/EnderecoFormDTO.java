package com.compass.projetodoacao.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.projetodoacao.entities.Endereco;

public class EnderecoFormDTO {
	
	private Integer id;
	
	@NotNull @NotEmpty
	private String numero;

	private String complemento;
	@NotNull @NotEmpty
	private String bairro;
	@NotNull @NotEmpty
	private String logradouro;
	@NotNull @NotEmpty
	private String cidade;
	@NotNull @NotEmpty
	private String estado;
	@NotNull @NotEmpty
	private String cep;
	
	public EnderecoFormDTO() {}
	
	public EnderecoFormDTO(Endereco obj) {
		this.id = obj.getId();
		this.numero = obj.getNumero();
		this.complemento= obj.getComplemento();
		this.bairro = obj.getBairro();
		this.logradouro = obj.getLogradouro();
		this.cidade = obj.getCidade();
		this.estado = obj.getEstado();
		this.cep = obj.getCep();
	}
	
	public EnderecoFormDTO(DoadorFormDTO doadorDTO) {

		this.numero = doadorDTO.getNumero();
		this.complemento= doadorDTO.getComplemento();
		this.bairro = doadorDTO.getBairro();
		this.logradouro = doadorDTO.getLogradouro();
		this.cidade = doadorDTO.getCidade();
		this.estado = doadorDTO.getEstado();
		this.cep = doadorDTO.getCep();
	}
	
	public EnderecoFormDTO(ONGFormDTO ongDTO) {

		this.numero = ongDTO.getNumero();
		this.complemento= ongDTO.getComplemento();
		this.bairro = ongDTO.getBairro();
		this.logradouro = ongDTO.getLogradouro();
		this.cidade = ongDTO.getCidade();
		this.estado = ongDTO.getEstado();
		this.cep = ongDTO.getCep();
	}
	
	public EnderecoFormDTO(DonatarioFormDTO donatarioDTO) {

		this.numero = donatarioDTO.getNumero();
		this.complemento= donatarioDTO.getComplemento();
		this.bairro = donatarioDTO.getBairro();
		this.logradouro = donatarioDTO.getLogradouro();
		this.cidade = donatarioDTO.getCidade();
		this.estado = donatarioDTO.getEstado();
		this.cep = donatarioDTO.getCep();
	}
	
	public Integer getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}
	
	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public String getCep() {
		return cep;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
}
