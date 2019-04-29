package br.com.randomthings.domain;

public enum StatusOrder {
	APROVADO("Aprovado", 1),
//	EMPROCESSAMENTO("Em Processamento", 2), 
	EMTRANSITO("Em Transporte", 2), 
	ENTREGUE("Entregue", 3);
//	EMTROCA("", 4);
//	TROCAAUTORIZADA("", 5),
//	
//	REPROVADO("", 7);
	
	private String description;
	
	private Integer sequence;
	
	StatusOrder(String description, Integer sequence) {
		this.description = description;
		this.sequence = sequence;
	}
	public String getDescription() {
		return description;
	}
	public Integer getOrder() {
		return sequence;
	}	
}
