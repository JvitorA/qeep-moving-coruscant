package br.com.qm.enums;

public enum Pasta {

	SAUDE("SAUDE"), EDUCACAO("EDUCACAO"), SEGURANCA("SEGURANCA");

	private String value;

	private Pasta(String value) {
		this.value = value;
	}	

	public String getValue() {
		return value;
	}
	
}
