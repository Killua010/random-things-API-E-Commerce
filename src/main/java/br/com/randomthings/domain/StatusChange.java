package br.com.randomthings.domain;

public enum StatusChange {
	EMTROCA("Em troca"),
	TROCAAUTORIZADA("Troca Autorizada"),
	TROCAREPROVADA("Troca Reprovada");
	
	private String description;
	
	StatusChange(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
