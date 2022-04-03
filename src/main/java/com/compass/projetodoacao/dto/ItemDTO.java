package com.compass.projetodoacao.dto;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.compass.projetodoacao.entities.Doacao;
import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.enums.Tipo;



public class ItemDTO {
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	private List<Doacao> doacoes;
	private Integer quantidade;
	
	
	public ItemDTO(Item item) {
		super();
		this.id = item.getId();
		this.tipo = item.getTipo();
		this.doacoes = item.getDoacoes();
		this.quantidade=item.getQuantidade();
	}
	public Integer getId() {
		return id;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public List<Doacao> getDoacoes() {
		return doacoes;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	
	
}
