package models;


import java.util.List;

import br.com.technolog.checklist.PerfilCond;

/**
 * Created by William on 18/05/17.
 * Classe java para a
 */

public class JornadaServiceResp {
	private Motorista motorista;
	private List<VeiculosUtilizado> veiculos;
	private InfoJornada infoJornada;
	private LastStop lastStop;
	private LastAbast lastAbast;
	private Combustivel combustivel;
	private PerfilCond perfil;
	private List<RPM> rpm;
	private LastPoi lastPoi;
	private TimeJorney timeJorney;

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public List<VeiculosUtilizado> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<VeiculosUtilizado> veiculos) {
		this.veiculos = veiculos;
	}

	public InfoJornada getInfoJornada() {
		return infoJornada;
	}

	public void setInfoJornada(InfoJornada infoJornada) {
		this.infoJornada = infoJornada;
	}

	public LastStop getLastStop() {
		return lastStop;
	}

	public void setLastStop(LastStop lastStop) {
		this.lastStop = lastStop;
	}

	public LastAbast getLastAbast() {
		return lastAbast;
	}

	public void setLastAbast(LastAbast lastAbast) {
		this.lastAbast = lastAbast;
	}

	public Combustivel getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}

	public PerfilCond getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilCond perfil) {
		this.perfil = perfil;
	}

	public List<RPM> getRpm() {
		return rpm;
	}

	public void setRpm(List<RPM> rpm) {
		this.rpm = rpm;
	}

	public LastPoi getLastPoi() {
		return lastPoi;
	}

	public void setLastPoi(LastPoi lastPoi) {
		this.lastPoi = lastPoi;
	}

	public TimeJorney getTimeJorney() {
		return timeJorney;
	}

	public void setTimeJorney(TimeJorney timeJorney) {
		this.timeJorney = timeJorney;
	}
}
