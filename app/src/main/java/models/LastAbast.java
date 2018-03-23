package models;

import utility.Utilidades;

import static utility.Utilidades.round;

/**
 * Created by William on 19/06/17.
 * Classe java para a
 */

public class LastAbast {
	private String data;
	private Float volumeAbastecido;
	private String nomePosto;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Float getVolumeAbastecido() {
		return volumeAbastecido;
	}

	public void setVolumeAbastecido(Float volumeAbastecido) {
		this.volumeAbastecido = volumeAbastecido;
	}

	public String getNomePosto() {
		return nomePosto;
	}

	public void setNomePosto(String nomePosto) {
		this.nomePosto = nomePosto;
	}

	public String getInfo() {
		String infos = Utilidades.formatar(data) + " " + round(volumeAbastecido, 2) + " L " + nomePosto;
		return infos;
	}
}
