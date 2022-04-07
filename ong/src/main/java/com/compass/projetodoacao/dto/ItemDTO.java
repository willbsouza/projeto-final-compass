package com.compass.projetodoacao.dto;

import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.enums.Tipo;

public class ItemDTO {
	
	private Integer id;
	private Tipo tipo;
	private Integer quantidadeTotal;
	
	public ItemDTO() {}
	
	public ItemDTO(Item item) {
		this.id = item.getId();
		this.tipo = item.getTipo();
		this.quantidadeTotal=item.getQuantidadeTotal();
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
