package com.api.viavarejo.parcelamento.data;

import java.util.ArrayList;

public class Parcela {

	private int numeroParcela;
	private double valor;
	private double taxaJurosAoMes;
	
	public int getNumeroParcela() {
		return numeroParcela;
	}
	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getTaxaJurosAoMes() {
		return taxaJurosAoMes;
	}
	public void setTaxaJurosAoMes(double taxaJurosAoMes) {
		this.taxaJurosAoMes = taxaJurosAoMes;
	}

}
