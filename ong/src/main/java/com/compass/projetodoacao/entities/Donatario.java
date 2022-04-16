package com.compass.projetodoacao.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Donatario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@NotNull
	private String nome;
	
	@NotEmpty
	@NotNull
	private String cpf;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_donatario")
	private List<Solicitacao> solicitacoes;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_donatario")
	private List<Telefone> telefones = new ArrayList<Telefone>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_donatario")
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}
	
	public void adicionarTelefone(Telefone telefone) {
		this.telefones.add(telefone);
	}

	public void adicionarEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
	}	
}
