package br.com.qm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.qm.enums.Pasta;

@Entity
public class Secretaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_secretaria")
	private long idSecretaria;

	private String nome;

	@Enumerated(EnumType.STRING)
	private Pasta pasta;

	@Column(name = "orcamento_folha")
	private double orcamentoFolha;

	@Column(name = "orcamento_projeto")
	private double orcamentoProjeto;

	@OneToMany(mappedBy = "secretaria", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Projeto> projetos;

	@JsonManagedReference
	@OneToMany(mappedBy = "secretaria", cascade = CascadeType.ALL)
	private List<Servidor> servidores;

	public Secretaria(long idSecretaria, String nome, Pasta pasta, double orcamentoFolha, double orcamentoProjeto,
			List<Projeto> projetos, List<Servidor> servidores) {
		super();
		this.idSecretaria = idSecretaria;
		this.nome = nome;
		this.pasta = pasta;
		this.orcamentoFolha = orcamentoFolha;
		this.orcamentoProjeto = orcamentoProjeto;
		this.projetos = projetos;
		this.servidores = servidores;
	}

	public Secretaria(String nome, Pasta pasta, double orcamentoFolha, double orcamentoProjeto) {
		this.nome = nome;
		this.pasta = pasta;
		this.orcamentoFolha = orcamentoFolha;
		this.orcamentoProjeto = orcamentoProjeto;
	}

	public Secretaria() {
	}

	public long getIdSecretaria() {
		return idSecretaria;
	}

	public void setIdSecretaria(long idSecretaria) {
		this.idSecretaria = idSecretaria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pasta getPasta() {
		return pasta;
	}

	public void setPasta(Pasta pasta) {
		this.pasta = pasta;
	}

	public double getOrcamentoFolha() {
		return orcamentoFolha;
	}

	public void setOrcamentoFolha(double orcamentoFolha) {
		this.orcamentoFolha = orcamentoFolha;
	}

	public double getOrcamentoProjeto() {
		return orcamentoProjeto;
	}

	public void setOrcamentoProjeto(double orcamentoProjeto) {
		this.orcamentoProjeto = orcamentoProjeto;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public List<Servidor> getServidores() {
		return servidores;
	}

	public void setServidores(List<Servidor> servidores) {
		this.servidores = servidores;
	}

}
