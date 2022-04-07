package com.compass.projetodoacao.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.compass.projetodoacao.entities.enums.Tipo;

public class DoacaoFormDTO {
	
	private Integer id;
	
	@NotNull
	private Integer id_doador;
	
	@NotNull
	private Integer id_ong;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Tipo tipoItem;
	
	@NotNull
	private Integer quantidadeItem;
	
	@NotNull
	private Integer id_categoria;

	public Integer getId() {
		return id;
	}

	public Integer getId_doador() {
		return id_doador;
	}

	public Integer getId_ong() {
		return id_ong;
	}
	
	public Tipo getTipoItem() {
		return tipoItem;
	}

	public Integer getQuantidadeItem() {
		return quantidadeItem;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}
}
