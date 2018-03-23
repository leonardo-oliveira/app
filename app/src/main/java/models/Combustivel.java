package models;

import static utility.Utilidades.round;

/**
 * Created by William on 19/06/17.
 * Classe java para a
 */

public class Combustivel {
	private Float consumo;
	private Float fuel;
	private Float kmPerco;
	private Float media;
	private Integer picos;
	private Float tempoParadoLigado;
	private Float autonomia;

	public String getConsumo() {
		if (consumo == null || consumo == 0)
			return "--";
		return String.valueOf(round(consumo, 2)) + "L";
	}

	public void setConsumo(Float consumo) {
		this.consumo = consumo;
	}

	public Float getKmPerco() {
		if (kmPerco == null)
			return 0f;
		return kmPerco;
	}

	public void setKmPerco(Float kmPerco) {
		this.kmPerco = kmPerco;
	}

	public String getMedia() {
		if (media == null || media == 0) {
			return "--";
		}
		return String.valueOf(round(media, 2)) + "KM/L";
	}

	public void setMedia(Float media) {
		this.media = media;
	}

	public String getPicos() {
		if (picos == null)
			return "--";
		return String.valueOf(picos);
	}

	public void setPicos(Integer picos) {
		this.picos = picos;
	}

	public Float getTempoParadoLigado() {
		return tempoParadoLigado;
	}

	public void setTempoParadoLigado(Float tempoParadoLigado) {
		this.tempoParadoLigado = tempoParadoLigado;
	}

	public Float getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(Float autonomia) {
		this.autonomia = autonomia;
	}

	public String getFuel() {
		if (fuel == null || fuel == 0) {
			return "--";
		}
		return String.valueOf(round(fuel, 2)) + " L";
	}

	public void setFuel(Float fuel) {
		this.fuel = fuel;
	}

	public String calcAutonomia() {
		if (media == 0) {
			return "--";
		} else {
			String autonomia = String.valueOf(round(fuel * media, 2));
			return autonomia + " KM";
		}
	}



	@Override
	public String toString() {
		return "{\"Combustivel\":{"
				+ "\"consumo\":\"" + consumo + "\""
				+ ", \"fuel\":\"" + fuel + "\""
				+ ", \"kmPerco\":\"" + kmPerco + "\""
				+ ", \"media\":\"" + media + "\""
				+ ", \"picos\":\"" + picos + "\""
				+ ", \"tempoParadoLigado\":\"" + tempoParadoLigado + "\""
				+ ", \"autonomia\":\"" + autonomia + "\""
				+ "}}";
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
}
