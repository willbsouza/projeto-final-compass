package com.compass.projetodoacao.dto;

import java.time.LocalDate;

import com.compass.projetodoacao.entities.Solicitacao;
import com.compass.projetodoacao.entities.enums.Status;
import com.compass.projetodoacao.entities.enums.Tipo;

public class SolicitacaoDTO {
	
	private Integer id;
	private LocalDate dataCadastro;
	private String nomeDonatario;
	private String filialOng;
	private Status status;
	private Tipo item;
	private Integer quantidadeItem;
	
	public SolicitacaoDTO() {}
	
	public SolicitacaoDTO(Solicitacao solicitacao) {
		this.id = solicitacao.getId();
		this.dataCadastro = solicitacao.getDataCadastro();
		this.nomeDonatario = solicitacao.getDonatario().getNome();
		this.filialOng = solicitacao.getOng().getFilial();
		this.status = solicitacao.getStatus();
		this.item = solicitacao.getItem().getTipo();
		this.quantidadeItem = solicitacao.getQuantidade();
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public String getNomeDonatario() {
		return nomeDonatario;
	}

	public String getFilialOng() {
		return filialOng;
	}
	
	public Status getStatus() {
		return status;
	}

	public Tipo getItem() {
		return item;
	}

	public Integer getQuantidadeItem() {
		return quantidadeItem;
	}
}
