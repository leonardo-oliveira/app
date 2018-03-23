package models;

/**
 * Created by William on 05/04/17.
 * Classe java para a
 */

public class Usuarios {
	private String cod;
	private String login;
	private String senha;
	private Integer cod_cliente;
	private String razao_social;
	private String caminho_logo;
	private Integer tipoUsuario;


	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(Integer cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getCaminho_logo() {
		return caminho_logo;
	}

	public void setCaminho_logo(String caminho_logo) {
		this.caminho_logo = caminho_logo;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


	@Override
	public String toString() {
		return "{\"Usuarios\":{"
				+ "\"cod\":\"" + cod + "\""
				+ ", \"login\":\"" + login + "\""
				+ ", \"senha\":\"" + senha + "\""
				+ ", \"cod_cliente\":\"" + cod_cliente + "\""
				+ ", \"razao_social\":\"" + razao_social + "\""
				+ ", \"caminho_logo\":\"" + caminho_logo + "\""
				+ ", \"tipoUsuario\":\"" + tipoUsuario + "\""
				+ "}}";
	}
}
