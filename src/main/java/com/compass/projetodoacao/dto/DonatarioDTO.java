package com.compass.projetodoacao.dto;

import com.compass.projetodoacao.entities.Donatario;

public class DonatarioDTO {
	
	private Integer id;
	private String cpf;
	private String nome;
	
	public DonatarioDTO() {};
	
	public DonatarioDTO(Donatario obj) {
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
