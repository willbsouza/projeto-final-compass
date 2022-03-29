package com.compass.projetodoacao.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.projetodoacao.entities.Item;
import com.compass.projetodoacao.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DoacaoFormDTO {
	
	//Doacao
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private LocalDate dataCadastro;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@NotNull
	private Integer id_doador;
	
	@NotNull
	private Integer id_ong;
	
	//Item
	
	@NotEmpty
	@NotNull
	private String nomeItem;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private Integer id_doacao;
	
	@NotNull
	private Integer id_categoria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getId_doacao() {
		return id_doacao;
	}

	public void setId_doacao(Integer id_doacao) {
		this.id_doacao = id_doacao;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}
}
