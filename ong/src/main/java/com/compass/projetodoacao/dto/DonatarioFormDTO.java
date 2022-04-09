package com.compass.projetodoacao.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DonatarioFormDTO {
	
	private Integer id;
	
	@NotNull @NotEmpty
	private String nomeDonatario;
	@NotNull @NotEmpty
	private String cpfDonatario;
	
	public Integer getId() {
		return id;
	}
	
	public String getNomeDonatario() {
		return nomeDonatario;
	}
	
	public String getCpfDonatario() {
		return cpfDonatario;
	}
}
