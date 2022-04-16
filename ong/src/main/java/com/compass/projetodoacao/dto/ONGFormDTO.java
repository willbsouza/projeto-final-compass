package com.compass.projetodoacao.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ONGFormDTO {

	@NotNull @NotEmpty
	private String filialONG;
	@NotNull @NotEmpty
	private String cnpj;
	
	public String getFilialONG() {
		return filialONG;
	}

	public void setFilialONG(String filialONG) {
		this.filialONG = filialONG;
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
