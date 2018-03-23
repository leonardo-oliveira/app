package models;

/**
 * Created by William on 20/01/17.
 * Classe java para a
 */

public class Usuario {
	private String cod;
	private String login;
	private String senha;
	private int cod_cliente;
	private String razao_social;
	private String caminho_logo;
	private int tipoUsuario; // 1 t_CLIENTE PORTAL // 2 t_motoristas

	public Usuario(String cod, String login, String senha, int cod_cliente, String razao_social, String caminho_logo) {
		this.cod = cod;
		this.login = login;
		this.senha = senha;
		this.cod_cliente = cod_cliente;
		this.razao_social = razao_social;
		this.caminho_logo = caminho_logo;
	}

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

	public int getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(int cod_cliente) {
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

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


	@Override
	public String toString() {
		return "{\"Usuario\":{"
				+ "\"caminho_logo\":\"" + caminho_logo + "\""
				+ ", \"cod\":\"" + cod + "\""
				+ ", \"login\":\"" + login + "\""
				+ ", \"senha\":\"" + senha + "\""
				+ ", \"cod_cliente\":\"" + cod_cliente + "\""
				+ ", \"razao_social\":\"" + razao_social + "\""
				+ ", \"tipoUsuario\":\"" + tipoUsuario + "\""
				+ "}}";
	}
}
