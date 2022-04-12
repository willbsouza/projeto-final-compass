package com.compass.projetodoacao.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ONGPostFormDTO {

	private Integer id;
	
	@NotNull @NotEmpty
	private String filialONG;
	
	@NotNull @NotEmpty
	private String telefone;
	
	@NotNull @NotEmpty
	private String logradouro;
	@NotNull @NotEmpty
	private String numero;
	
	private String complemento;
	@NotNull @NotEmpty
	private String bairro;
	@NotNull @NotEmpty
	private String cidade;
	@NotNull @NotEmpty
	private String estado;
	@NotNull @NotEmpty
	private String cep;
	
	public Integer getId() {
		return id;
	}
	public String getFilialONG() {
		return filialONG;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public String getComplemento() {
		return complemento;
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
	public void setId(Integer id) {
		this.id = id;
	}
	public void setFilialONG(String filialONG) {
		this.filialONG = filialONG;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
}
