package models;

import utility.Utilidades;

/**
 * Created by William on 26/05/17.
 * Classe java para a
 */

public class InfoJornada {
	private String endereco;
	private String dataInicio;
	private Float tempoIntra;

	public String getEndereco() {
		if (endereco != null)
			return endereco;
		return "--";
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataInicio() {
		if (dataInicio != null)
			return Utilidades.formatar(dataInicio);
		return "--";
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getInfos() {

		return getDataInicio() + " " + getEndereco();
	}

	public Float getTempoIntraFloat(){
		if (tempoIntra == null)
			return 0.f;
		return tempoIntra;
	}
	public String getTempoIntra() {
		if (tempoIntra != null)
			return String.valueOf(minutosHoras(Math.round(tempoIntra*60)));
		return "--";
	}

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

	public void setTempoIntra(Float tempoIntra) {
		this.tempoIntra = tempoIntra;
	}

	public String getInfoIntra() {

		return getEndereco() + " " + getTempoIntra();
	}
}
