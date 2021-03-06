package br.com.randomthings.domain;

public enum StatusOrder {
	REPROVADO("Reprovado", 0),
	EMPROCESSAMENTO("Em processamento", 1),
	APROVADO("Aprovado", 2),
	EMTRANSITO("Em Transporte", 3), 
	ENTREGUE("Entregue", 4);

	
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
