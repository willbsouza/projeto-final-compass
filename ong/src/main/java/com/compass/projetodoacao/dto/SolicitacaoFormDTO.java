package com.compass.projetodoacao.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.compass.projetodoacao.entities.enums.Tipo;

public class SolicitacaoFormDTO {
	
	private Integer id;

	@NotNull
	private Integer id_donatario;
	
	@NotNull
	private Integer id_ong;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Tipo tipoItem;
	
	@NotNull
	private Integer quantidadeItem;

	public Integer getId_donatario() {
		return id_donatario;
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

	public void setId_donatario(Integer id_donatario) {
		this.id_donatario = id_donatario;
	}

	public void setId_ong(Integer id_ong) {
		this.id_ong = id_ong;
	}

	public void setTipoItem(Tipo tipoItem) {
		this.tipoItem = tipoItem;
	}

	public void setQuantidadeItem(Integer quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}
  	public void setId(Integer id) {
		this.id = id;
	}

	public void setId_donatario(Integer id_donatario) {
		this.id_donatario = id_donatario;
	}

	public void setId_ong(Integer id_ong) {
		this.id_ong = id_ong;
	}

	public void setTipoItem(Tipo tipoItem) {
		this.tipoItem = tipoItem;
	}

	public void setQuantidadeItem(Integer quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}
}
