package com.compass.projetodoacao.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.compass.projetodoacao.entities.enums.Tipo;

public class DoacaoFormDTO {
	
	//Doacao
	private Integer id;
	
	@NotNull
	private Integer id_doador;
	
	@NotNull
	private Integer id_ong;
	
	//Item
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Tipo tipoItem;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private Integer id_categoria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_doador() {
		return id_doador;
	}

	public void setId_doador(Integer id_doador) {
		this.id_doador = id_doador;
	}

	public Integer getId_ong() {
		return id_ong;
	}

	public void setId_ong(Integer id_ong) {
		this.id_ong = id_ong;
	}

	public Tipo getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(Tipo tipoItem) {
		this.tipoItem = tipoItem;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}
}
