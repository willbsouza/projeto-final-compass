package com.compass.transportador.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Transporte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty
	private String enderecoOrigem;
	
	@NotNull
	@NotEmpty
	private String enderecoDestino;
	
	@NotNull
	@NotEmpty
	private String item;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private LocalDate dataPedido;
	
	@NotNull
	private LocalDate dataPrevisaoServico;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnderecoOrigem() {
		return enderecoOrigem;
	}

	public void setEnderecoOrigem(String enderecoOrigem) {
		this.enderecoOrigem = enderecoOrigem;
	}

	public String getEnderecoDestino() {
		return enderecoDestino;
	}

	public void setEnderecoDestino(String enderecoDestino) {
		this.enderecoDestino = enderecoDestino;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataPrevisaoServico() {
		return dataPrevisaoServico;
	}

	public void setDataPrevisaoServico(LocalDate dataPrevisaoServico) {
		this.dataPrevisaoServico = dataPrevisaoServico;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}