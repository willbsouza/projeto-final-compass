package com.compass.projetodoacao.dto;

import com.compass.projetodoacao.entities.Doador;



public class DoadorDTO {
	private String Cpf;
	private String nome;
	private Integer id;
	
	public DoadorDTO() {};
	public DoadorDTO(Doador obj) {
		this.Cpf = obj.getCpf();
		this.nome = obj.getNome();
		this.id = obj.getId();
	}
	public Integer getId() {
		return id;
	}
	public String getCpf() {
		return Cpf;
	}
	public String getNome() {
		return nome;
	}
	
	
	
	
	
}
