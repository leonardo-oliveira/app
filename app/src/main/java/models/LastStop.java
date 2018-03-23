package models;

import utility.Utilidades;

/**
 * Created by William on 19/06/17.
 * Classe java para a
 */

public class LastStop {
	private String endereco;
	private String dataInicio;
	private Float tempoParadoMinutos;
	private Integer tempoSemParar;

	public String getTempoSemParar() {
		if(tempoSemParar == null)
			return "--";
		return minutosHoras(tempoSemParar);
	}

	public void setTempoSemParar(Integer tempoSemParar) {
		this.tempoSemParar = tempoSemParar;
	}

	public String getEndereco() {
		if(endereco == null)
			return "--";
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataInicio() {
		if(dataInicio == null)
			return "--";
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getTempoParadoMinutos() {
		if(tempoParadoMinutos == null)
			return "--";
		return String.valueOf(minutosHoras(Math.round(tempoParadoMinutos)));
	}

	public void setTempoParadoMinutos(Float tempoParadoMinutos) {
		this.tempoParadoMinutos = tempoParadoMinutos;
	}

	/*
	*
	*
	*/
	public String minutosHoras(Integer minutos){
		Integer horas= minutos/60;
		Integer newMinutes = minutos%60;
		if(horas == 1){
			return String.valueOf(horas)+" hora e "+String.valueOf(newMinutes)+" minutos";
		}else if(horas == 0){
			return String.valueOf(newMinutes)+" minutos";
		}
		return String.valueOf(horas)+" horas e "+String.valueOf(newMinutes)+" minutos";
	}

	public String getInfo(){
		String string = getEndereco()+ " "+ String.valueOf(getTempoParadoMinutos())+" minutos";
		return string;
	}
}
