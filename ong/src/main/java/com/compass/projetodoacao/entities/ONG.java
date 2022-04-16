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
public class ONG {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty
	private String filial;
	
	@NotNull
	@NotEmpty
	private String cnpj;
	
	@NotNull
	@NotEmpty
	private String senha;
	
	@OneToMany
	@JoinColumn(name = "id_ong")
	private List<Doacao> doacoes;
	
	@OneToMany
	@JoinColumn(name = "id_ong")
	private List<Solicitacao> solicitacoes;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_ong")
	private List<Telefone> telefones = new ArrayList<Telefone>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_ong")
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
	}

	public void setFilial(String filial) {
		this.filial = filial;
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
