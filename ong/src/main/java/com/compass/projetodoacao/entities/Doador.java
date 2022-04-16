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

import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
public class Doador{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@NotNull
	private String nome;
	
	@NotEmpty
	@NotNull
	private String cpf;
	
	@NotEmpty
	@NotNull
	private String senha;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_doador")
	private List<Doacao> doacoes;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_doador")
	private List<Telefone> telefones = new ArrayList<Telefone>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_doador")
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public List<Doacao> getDoacoes() {
		return doacoes;
	}
	
	public void adicionarTelefone(Telefone telefone) {
		this.telefones.add(telefone);
	}

	public void adicionarEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
	}	
}
