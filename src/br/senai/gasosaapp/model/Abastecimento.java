package br.senai.gasosaapp.model;

import java.util.Date;

import br.com.senai.gasosaapp.util.Utils;

public class Abastecimento {
	
	private Long id;
	private Date data;
	private Double valor;
	private Double listros;
	private Long kmRodados;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getListros() {
		return listros;
	}
	public void setListros(Double listros) {
		this.listros = listros;
	}
	public Long getKmRodados() {
		return kmRodados;
	}
	public void setKmRodados(Long kmRodados) {
		this.kmRodados = kmRodados;
	}
	
	@Override
	public String toString() {
		return Utils.dateToString(this.data, "dd/MM") +" - "
			 + "R$ " + Utils.doubleToString(this.valor) +" - "
			 + Utils.doubleToString(this.listros) +"L - "
			 + String.valueOf(this.kmRodados) + "Km";
	}
}
