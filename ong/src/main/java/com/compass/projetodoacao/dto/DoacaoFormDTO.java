package com.compass.projetodoacao.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.compass.projetodoacao.entities.Doador;
import com.compass.projetodoacao.entities.enums.Modalidade;
import com.compass.projetodoacao.entities.enums.Tipo;

public class DoacaoFormDTO {

	@NotNull
	private Integer id_doador;

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

	public DoacaoFormDTO() {

	}

	public DoacaoFormDTO(DoacaoPutFormDTO doacao, Doador doador) {
		this.id_categoria = doacao.getId_categoria();
		this.id_doador = doador.getId();
		this.id_ong = doacao.getId_ong();
		this.modalidade = doacao.getModalidade();
		this.quantidadeItem = doacao.getQuantidadeItem();
		this.tipoItem = doacao.getTipoItem();
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

	public Modalidade getModalidade() {
		return modalidade;
	}

	public Integer getQuantidadeItem() {
		return quantidadeItem;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_doador(Integer id_doador) {
		this.id_doador = id_doador;
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
