package br.com.randomthings.domain;

public enum StatusInactivation {
	FORADEMERCADO("Produto fora de mercado");
	
	private String description;
	
	StatusInactivation(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}	
}
