package com.compass.projetodoacao.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.compass.projetodoacao.entities.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@NotNull
	private Integer quantidadeTotal;
	
	@OneToMany(mappedBy = "item")
	@JsonIgnore()
	private List<Doacao> doacoes;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public List<Doacao> getDoacoes() {
		return doacoes;
	}

	public void adicionarDoacao(Doacao doacao) {
		this.doacoes.add(doacao);
	}
	
	public void removerDoacao(Doacao doacao) {
		this.doacoes.remove(doacao);
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
