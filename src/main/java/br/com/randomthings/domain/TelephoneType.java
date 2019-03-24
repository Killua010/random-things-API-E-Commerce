package br.com.randomthings.domain;

public enum TelephoneType {
	FIXO("Fixo"),
	MOVEL("Movel");
	
	private String description;
	
	TelephoneType(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}	
}
