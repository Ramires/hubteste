package com.hubteste.enums;

public enum TipoSimNao {

	SIM("S", "Sim"),
	NAO("N", "Não");

	private String codigo;
	private String descricao;

	private TipoSimNao(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
