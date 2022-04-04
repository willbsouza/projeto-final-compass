package com.compass.projetodoacao.dto;

import com.compass.projetodoacao.entities.Endereco;

public class EnderecoDTO {
	private Integer id;
	private String numero;
	private String complemento;
	private String bairro;
	private String logradouro;
	private String cidade;
	private String estado;
	private String cep;
	public EnderecoDTO(Endereco obj) {
		this.id = obj.getId();
		this.numero = obj.getNumero();
		this.complemento= obj.getComplemento();
		this.bairro = obj.getBairro();
		this.logradouro = obj.getLogradouro();
		this.cidade = obj.getCidade();
		this.estado = obj.getEstado();
		this.cep = obj.getCep();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
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
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
}
