package com.compass.projetodoacao.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.compass.projetodoacao.entities.enums.Modalidade;
import com.compass.projetodoacao.entities.enums.Tipo;

public class DoacaoPutFormDTO {
	
	@NotNull
	private Integer id_ong;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Tipo tipoItem;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Modalidade modalidade;
	
	@NotNull
	private Integer quantidadeItem;
	
	@NotNull
	private Integer id_categoria;

	public Integer getId_ong() {
		return id_ong;
	}
	
	public Tipo getTipoItem() {
		return tipoItem;
	}
	
	public Modalidade getModalidade() {
		return modalidade;
	}

	public Integer getQuantidadeItem() {
		return quantidadeItem;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_ong(Integer id_ong) {
		this.id_ong = id_ong;
	}

	public void setTipoItem(Tipo tipoItem) {
		this.tipoItem = tipoItem;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public void setQuantidadeItem(Integer quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}
}
