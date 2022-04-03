package com.compass.projetodoacao.dto;

import java.time.LocalDate;

import com.compass.projetodoacao.entities.Doacao;
import com.compass.projetodoacao.entities.enums.Tipo;


public class DoacaoDTO {
	
	private Integer id;
	private LocalDate dataCadastro;
	private String nomeDoador;
	private String filialOng;
	private Tipo item;
	private Integer quantidadeItem;
	
	public DoacaoDTO() {}
	
	public DoacaoDTO(Doacao doacao) {
		this.id = doacao.getId();
		this.dataCadastro = doacao.getDataCadastro();
		this.nomeDoador = doacao.getDoador().getNome();
		this.filialOng = doacao.getOng().getFilial();
		this.item = doacao.getItem().getTipo();
		this.quantidadeItem = doacao.getQuantidade();
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public String getNomeDoador() {
		return nomeDoador;
	}

	public String getFilialOng() {
		return filialOng;
	}

	public Tipo getItem() {
		return item;
	}

	public Integer getQuantidadeItem() {
		return quantidadeItem;
	}
}
