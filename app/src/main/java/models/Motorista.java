package models;

/**
 * Created by William on 18/05/17.
 * Classe java para a
 */

public class Motorista {
	private String cod;
	private String nome;
	private String caminhoArquivoFoto;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCaminhoArquivoFoto() {
		return caminhoArquivoFoto;
	}

	public void setCaminhoArquivoFoto(String caminhoArquivoFoto) {
		this.caminhoArquivoFoto = caminhoArquivoFoto;
	}


	@Override
	public String toString() {
		return "{\"Motorista\":{"
				+ "\"cod\":\"" + cod + "\""
				+ ", \"nome\":\"" + nome + "\""
				+ ", \"caminhoArquivoFoto\":\"" + caminhoArquivoFoto + "\""
				+ "}}";
	}
}
