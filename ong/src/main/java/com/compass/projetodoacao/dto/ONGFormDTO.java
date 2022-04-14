package com.compass.projetodoacao.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ONGFormDTO {

	@NotNull @NotEmpty
	private String filialONG;
	
	public String getFilialONG() {
		return filialONG;
	}

	public void setFilialONG(String filialONG) {
		this.filialONG = filialONG;
	}
}
