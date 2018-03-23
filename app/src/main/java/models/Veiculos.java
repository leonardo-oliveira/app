package models;

/**
 * Created by William on 31/01/17.
 * Classe java para a
 */

public class Veiculos {
	private Integer cod;
	private Integer codW;
	private String placa;

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getCodW() {
		return codW;
	}

	public void setCodW(Integer codW) {
		this.codW = codW;
	}


	@Override
	public String toString() {
		return "{\"Veiculos\":{"
				+ "\"cod\":\"" + cod + "\""
				+ ", \"codW\":\"" + codW + "\""
				+ ", \"placa\":\"" + placa + "\""
				+ "}}";
	}
}
