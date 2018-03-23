package models;

/**
 * Created by William on 23/06/17.
 * Classe java para a
 */

public class LastPoi {
	private String endereco;
	private String nomePoi;

	public String getEndereco() {
		if(endereco == null)
			return "--";
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomePoi() {
		if(nomePoi == null)
			return "--";
		return nomePoi;
	}

	public void setNomePoi(String nomePoi) {
		this.nomePoi = nomePoi;
	}

	public String getLastPoi(){
		if(this.getNomePoi().equals("--"))
			return "Não esteve em um ponto de interesse";

		return this.getNomePoi()+", "+this.getEndereco();
	}
}
