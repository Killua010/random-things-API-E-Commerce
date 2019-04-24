package br.com.randomthings.domain;

public enum StatusOrder {
	APROVADO("", 1),
//	EMPROCESSAMENTO("Em Processamento", 2), 
	EMTRANSITO("", 2), 
	ENTREGUE("", 3);
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
