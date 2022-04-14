package com.compass.projetodoacao.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DonatarioFormDTO {
	
	@NotNull @NotEmpty
	private String nomeDonatario;
	@NotNull @NotEmpty
	private String cpfDonatario;
	
	public String getNomeDonatario() {
		return nomeDonatario;
	}
	
	public String getCpfDonatario() {
		return cpfDonatario;
	}

	public void setNomeDonatario(String nomeDonatario) {
		this.nomeDonatario = nomeDonatario;
	}

	public void setCpfDonatario(String cpfDonatario) {
		this.cpfDonatario = cpfDonatario;
	}
}
