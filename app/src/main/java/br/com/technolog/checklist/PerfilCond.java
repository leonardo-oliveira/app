package br.com.technolog.checklist;

import static utility.Utilidades.round;

/**
 * Created by William on 20/06/17.
 * Classe java para a
 */

public class PerfilCond {
	private Float faixaEco;
	private Float foraFaixaEco;
	private Float foraFaixaSemAc;
	private Float picos;
	private Float kmPercorrido;
	private Float paradoLigado;
	private Float rolamento;
	public String getFaixaEco() {
		if (foraFaixaEco == null || faixaEco == null || faixaEco + foraFaixaEco == 0)
			return "--";
		Float percent = (faixaEco / (faixaEco + foraFaixaEco)) * 100;
		return String.valueOf(percent) + " %";
	}

	public void setFaixaEco(Float faixaEco) {
		this.faixaEco = faixaEco;
	}

	public String getForaFaixaEco() {
		if (foraFaixaEco == null || faixaEco == null || faixaEco + foraFaixaEco == 0)
			return "--";
		Float percent = (foraFaixaEco / (faixaEco + foraFaixaEco)) * 100;
		return String.valueOf(percent) + " %";
	}

	public void setForaFaixaEco(Float foraFaixaEco) {
		this.foraFaixaEco = foraFaixaEco;
	}

	public String getPicos() {
		return String.valueOf(picos);
	}

	public void setPicos(Float picos) {
		this.picos = picos;
	}

	public String getKmPercorrido() {
		if(kmPercorrido == null)
			return round(0f,0) + " KM";
		return String.valueOf(kmPercorrido) + " KM";
	}

	public void setKmPercorrido(Float kmPercorrido) {
		this.kmPercorrido = kmPercorrido;
	}

	public String getParadoLigado() {
		if(paradoLigado < 0){
			return minutosHoras(0);
		}
		return minutosHoras(Math.round(paradoLigado*60));
	}

	public void setParadoLigado(Float paradoLigado) {
		this.paradoLigado = paradoLigado;
	}

	public float getFaixaEcoInt() {
		return faixaEco;
	}

	public float getForaFaixaEcoInt() {
		return foraFaixaEco;
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

	public String getRolamentoString() {
		return String.valueOf(rolamento*60)+"%";
	}
	public Float getForaFaixaSemAc() {
		return foraFaixaSemAc;
	}

	public Float getRolamento() {
		return rolamento*60;
	}

	public void setRolamento(Float rolamento) {
		this.rolamento = rolamento;
	}

	public void setForaFaixaSemAc(Float foraFaixaSemAc) {
		this.foraFaixaSemAc = foraFaixaSemAc;
	}
}
