package com.hubteste.enums;

public enum SituacaoConta {

	ATIVA("A", "Ativo"),
	CANCELADA("C", "Cancelada"),
	BLOQUEADA("B", "Bloqueada");

	private String codigo;
	private String descricao;

	private SituacaoConta(String codigo, String descricao) {
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
