package com.compass.projetodoacao.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ONG {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty
	private String filial;
	
	@OneToMany(mappedBy = "ong")
	@JsonIgnore()
	private List<Doacao> doacoes;
	
	@OneToMany
	@JoinColumn(name = "idOng")
	private List<Telefone> telefones = new ArrayList<Telefone>();
	
	@OneToMany
	@JoinColumn(name = "idOng")
	private List<Endereco> enderecos = new ArrayList<Endereco>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public List<Doacao> getDoacoes() {
		return doacoes;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	
	public void adicionarTelefone(Telefone telefone) {
		this.telefones.add(telefone);
	}

	public void adicionarEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
	}
}
