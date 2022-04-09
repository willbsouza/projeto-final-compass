package com.compass.projetodoacao.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ONGFormDTO {

	private Integer id;
	
	@NotNull @NotEmpty
	private String filialONG;
	
	public Integer getId() {
		return id;
	}
	public String getFilialONG() {
		return filialONG;
	}
}
