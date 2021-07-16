package br.com.qm.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AumentoDTO {

	
	@NotNull
	@Min(value = 1)
	private double valorAumento;

	public AumentoDTO(double valorAumento) {
		super();
		this.valorAumento = valorAumento;
	}

	public double getValorAumento() {
		return valorAumento;
	}

	public void setValorAumento(double valorAumento) {
		this.valorAumento = valorAumento;
	}
	
}
