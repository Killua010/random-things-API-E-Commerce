package br.com.randomthings.domain;

public enum StatusOrder {
	EMPROCESSAMENTO("Em Processamento"), 
	EMTRANSITO(""), 
	ENTREGUE(""),
	EMTROCA(""),
	TROCAAUTORIZADA(""),
	APROVADO(""),
	REPROVADO("");
	
	private String description;
	
	StatusOrder(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}	
}
