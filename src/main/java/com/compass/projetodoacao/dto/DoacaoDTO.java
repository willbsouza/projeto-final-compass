package com.compass.projetodoacao.dto;

import java.time.LocalDate;

import com.compass.projetodoacao.entities.Doacao;


public class DoacaoDTO {
	private Integer id;
	private LocalDate dataCadastro;
	private DoadorDTO doador;
	private ONGDTO ong;
	private ItemDTO item;
	
	
	public DoacaoDTO(Doacao doacao) {
		this.id = doacao.getId();
		this.dataCadastro = doacao.getDataCadastro();
	
	}


	public Integer getId() {
		return id;
	}


	public LocalDate getDataCadastro() {
		return dataCadastro;
	}


	public DoadorDTO getDoador() {
		return doador;
	}


	public ONGDTO getOng() {
		return ong;
	}


	public ItemDTO getItem() {
		return item;
	}

}
