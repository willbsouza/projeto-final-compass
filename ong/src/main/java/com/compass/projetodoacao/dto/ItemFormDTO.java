package com.compass.projetodoacao.dto;

import javax.validation.constraints.NotNull;

import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.enums.Tipo;

public class ItemFormDTO {

	@NotNull
	private Integer idCategoria;
	
	@NotNull
	private Tipo tipo;
	
	@NotNull
	private Integer quantidadeTotal;
	
	public ItemFormDTO() {}
	
	public ItemFormDTO(Item item) {
		this.tipo = item.getTipo();
		this.idCategoria = item.getCategoria().getId();
		this.quantidadeTotal=item.getQuantidadeTotal();
	}
	
	public Integer getIdCategoria() {
		return idCategoria;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}
}
