package com.compass.projetodoacao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.projetodoacao.dto.DoadorPostFormDTO;
import com.compass.projetodoacao.dto.DonatarioPostFormDTO;
import com.compass.projetodoacao.dto.ONGPostFormDTO;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty
	private String logradouro;
	
	@NotNull
	@NotEmpty
	private String numero;

	private String complemento;
	
	@NotNull
	@NotEmpty
	private String bairro;
	
	@NotNull
	@NotEmpty
	private String cidade;
	
	@NotNull
	@NotEmpty
	private String estado;
	
	@NotNull
	@NotEmpty
	private String cep;
	
	public Endereco() {
		
	}
	
	public Endereco(DoadorPostFormDTO doadorDTO) {
		this.logradouro = doadorDTO.getLogradouro();
		this.numero = doadorDTO.getNumero();
		this.complemento = doadorDTO.getComplemento();
		this.bairro = doadorDTO.getBairro();
		this.cidade = doadorDTO.getCidade();
		this.estado = doadorDTO.getEstado();
		this.cep = doadorDTO.getCep();
	}
	
	public Endereco(DonatarioPostFormDTO donatarioDTO) {
		this.logradouro = donatarioDTO.getLogradouro();
		this.numero = donatarioDTO.getNumero();
		this.complemento = donatarioDTO.getComplemento();
		this.bairro = donatarioDTO.getBairro();
		this.cidade = donatarioDTO.getCidade();
		this.estado = donatarioDTO.getEstado();
		this.cep = donatarioDTO.getCep();
	}
	
	public Endereco(ONGPostFormDTO ongDTO) {
		this.logradouro = ongDTO.getLogradouro();
		this.numero = ongDTO.getNumero();
		this.complemento = ongDTO.getComplemento();
		this.bairro = ongDTO.getBairro();
		this.cidade = ongDTO.getCidade();
		this.estado = ongDTO.getEstado();
		this.cep = ongDTO.getCep();
	}
		
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return logradouro + ", " + numero + ", " + complemento
				+ ", " + bairro + ", " + cidade + ", " + estado + ", cep " + cep;
	}
}