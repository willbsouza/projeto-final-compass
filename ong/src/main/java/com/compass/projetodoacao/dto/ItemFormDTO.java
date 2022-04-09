package com.compass.projetodoacao.dto;

import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.enums.Tipo;

public class ItemFormDTO {
	
	private Integer id;
	private Integer idCategoria;
	private Tipo tipo;
	private Integer quantidadeTotal;
	
	public ItemFormDTO() {}
	
	public ItemFormDTO(Item item) {
		this.id = item.getId();
		this.tipo = item.getTipo();
		this.idCategoria = item.getCategoria().getId();
		this.quantidadeTotal=item.getQuantidadeTotal();
	}
	
	public Integer getIdCategoria() {
		return idCategoria;
	}
	
	public Integer getId() {
		return id;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}
}
