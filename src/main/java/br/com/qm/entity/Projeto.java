package br.com.qm.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_projeto")
	private long idProjeto;
	
	private String nome;
	
	private double custo;

	@Column(name = "data_prevista_conclusao")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataPrevistaConclusao;
	
	@Column(name = "data_conclusao")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataConclusao;
	
	private boolean concluido;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "secretaria_fk", referencedColumnName = "id_secretaria")
	private Secretaria secretaria;

	public Projeto(long idProjeto, String nome, double custo, LocalDate dataPrevistaConclusao, LocalDate dataConclusao,
			boolean concluido, Secretaria secretaria) {
		super();
		this.idProjeto = idProjeto;
		this.nome = nome;
		this.custo = custo;
		this.dataPrevistaConclusao = dataPrevistaConclusao;
		this.dataConclusao = dataConclusao;
		this.concluido = concluido;
		this.secretaria = secretaria;
	}
	
	public Projeto() {
		super();
	}

	public long getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(long idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public LocalDate getDataPrevistaConclusao() {
		return dataPrevistaConclusao;
	}

	public void setDataPrevistaConclusao(LocalDate dataPrevistaConclusao) {
		this.dataPrevistaConclusao = dataPrevistaConclusao;
	}

	public LocalDate getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDate dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

}
