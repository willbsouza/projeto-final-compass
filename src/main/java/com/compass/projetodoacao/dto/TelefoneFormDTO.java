package com.compass.projetodoacao.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TelefoneFormDTO {
	
	private Integer id;
	
	@NotNull
	@NotEmpty
	private String numero;
	
	public TelefoneFormDTO() {
		
	}
	
	public TelefoneFormDTO(DoadorFormDTO doadorDTO) {
		this.numero = doadorDTO.getTelefone();
	}
	
	public TelefoneFormDTO(ONGFormDTO ongDTO) {
		this.numero = ongDTO.getTelefone();
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
}
