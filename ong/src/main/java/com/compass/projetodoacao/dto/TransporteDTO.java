package com.compass.projetodoacao.dto;

import java.time.LocalDate;

public class TransporteDTO {

	private Integer idDoacao;
	
	private String enderecoOrigem;
	
	private String enderecoDestino;
	
	private String item;
	
	private Integer quantidade;
	
	private LocalDate dataPedido;
	
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
