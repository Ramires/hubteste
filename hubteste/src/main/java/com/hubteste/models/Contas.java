package com.hubteste.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Contas implements Serializable {

	private static final long serialVersionUID = -3254923586612733404L;

	@Id
	private Long id;

	@Column
	private String nome;

	@Column
	private Date data;

	@Column
	private String matriz;

	@Column
	private BigDecimal saldo;

	@Column
	private String situacao;

	@OneToOne
	private Contas contas;

	@OneToOne
	@JoinColumn(name = "juridica_id")
	private Juridica juridica;

	@OneToOne
	@JoinColumn(name = "fisica_id")
	private Fisica fisica;

	private String transacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Contas getContas() {
		return contas;
	}

	public void setContas(Contas contas) {
		this.contas = contas;
	}

	public Juridica getJuridica() {
		return juridica;
	}

	public void setJuridica(Juridica juridica) {
		this.juridica = juridica;
	}

	public Fisica getFisica() {
		return fisica;
	}

	public void setFisica(Fisica fisica) {
		this.fisica = fisica;
	}

	public String getMatriz() {
		return matriz;
	}

	public void setMatriz(String matriz) {
		this.matriz = matriz;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getTransacao() {
		return transacao;
	}

	public void setTransacao(String transacao) {
		this.transacao = transacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
