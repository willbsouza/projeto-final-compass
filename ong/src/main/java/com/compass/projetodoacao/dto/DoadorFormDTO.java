package com.compass.projetodoacao.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DoadorFormDTO {
	
	private Integer id;
	
	@NotNull @NotEmpty
	private String nomeDoador;
	@NotNull @NotEmpty
	private String cpfDoador;
	
	public Integer getId() {
		return id;
	}
	public String getNomeDoador() {
		return nomeDoador;
	}
	public String getCpfDoador() {
		return cpfDoador;
	}
}
