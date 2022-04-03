package com.compass.projetodoacao.dto;

import com.compass.projetodoacao.entities.Doador;

public class DoadorDTO {
	
	private Integer id;
	private String cpf;
	private String nome;
	
	public DoadorDTO() {};
	
	public DoadorDTO(Doador obj) {
		this.id = obj.getId();
		this.cpf = obj.getCpf();
		this.nome = obj.getNome();
	}
	
	public Integer getId() {
		return id;
	}
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
}
