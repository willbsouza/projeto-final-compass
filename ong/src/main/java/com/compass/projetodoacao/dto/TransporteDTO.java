package com.compass.projetodoacao.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TransporteDTO {

	@NotNull
	private Integer idDoacao;
	
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
	
	public Integer getIdDoacao() {
		return idDoacao;
	}
	
	public void setIdDoacao(Integer idDoacao) {
		this.idDoacao = idDoacao;
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
}
