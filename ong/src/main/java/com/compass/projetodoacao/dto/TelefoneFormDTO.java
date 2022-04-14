package com.compass.projetodoacao.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TelefoneFormDTO {

	@NotNull
	@NotEmpty
	private String numero;
	
	public TelefoneFormDTO() {
		
	}
	
	public TelefoneFormDTO(DoadorPostFormDTO doadorDTO) {
		this.numero = doadorDTO.getTelefone();
	}
	
	public TelefoneFormDTO(ONGPostFormDTO ongDTO) {
		this.numero = ongDTO.getTelefone();
	}
	
	public TelefoneFormDTO(DonatarioPostFormDTO donatarioDTO) {
		this.numero = donatarioDTO.getTelefone();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
