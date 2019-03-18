package br.com.randomthings.domain;

public enum StatusActivation {
	RETORNOPRODUTONOMERCADO("Retorno do produto no mercado");
	
	private String description;
	
	StatusActivation(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}	
}
