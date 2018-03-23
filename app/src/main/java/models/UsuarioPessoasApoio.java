package models;

/**
 * Created by William on 09/01/17.
 * Classe java para a
 */

public class UsuarioPessoasApoio {
	private Integer cod;
	private Integer codPessoaApoio;
	private String usuario;
	private String senha;
	private String flgAtivo;
	private String dataCadastro;
	private Integer codUsuarioClienteResp;
	private Integer codUsuarioResp;
	private String dataAcao;

	//Construtor

	public UsuarioPessoasApoio() {

	}

	public UsuarioPessoasApoio(Integer cod, Integer codPessoaApoio, String usuario, String senha, String flgAtivo, String dataCadastro, Integer codUsuarioClienteResp, Integer codUsuarioResp, String dataAcao) {
		this.cod = cod;
		this.codPessoaApoio = codPessoaApoio;
		this.usuario = usuario;
		this.senha = senha;
		this.flgAtivo = flgAtivo;
		this.dataCadastro = dataCadastro;
		this.codUsuarioClienteResp = codUsuarioClienteResp;
		this.codUsuarioResp = codUsuarioResp;
		this.dataAcao = dataAcao;
	}

	//Getter

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public Integer getCodPessoaApoio() {
		return codPessoaApoio;
	}

	public void setCodPessoaApoio(Integer codPessoaApoio) {
		this.codPessoaApoio = codPessoaApoio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFlgAtivo() {
		return flgAtivo;
	}

	//Setter

	public void setFlgAtivo(String flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getCodUsuarioClienteResp() {
		return codUsuarioClienteResp;
	}

	public void setCodUsuarioClienteResp(Integer codUsuarioClienteResp) {
		this.codUsuarioClienteResp = codUsuarioClienteResp;
	}

	public Integer getCodUsuarioResp() {
		return codUsuarioResp;
	}

	public void setCodUsuarioResp(Integer codUsuarioResp) {
		this.codUsuarioResp = codUsuarioResp;
	}

	public String getDataAcao() {
		return dataAcao;
	}

	public void setDataAcao(String dataAcao) {
		this.dataAcao = dataAcao;
	}

	@Override
	public String toString() {
		return "UsuarioPessoasApoio{" +
				"cod=" + cod +
				", codPessoaApoio=" + codPessoaApoio +
				", usuario='" + usuario + '\'' +
				", senha='" + senha + '\'' +
				", flgAtivo='" + flgAtivo + '\'' +
				", dataCadastro=" + dataCadastro +
				", codUsuarioClienteResp=" + codUsuarioClienteResp +
				", codUsuarioResp=" + codUsuarioResp +
				", dataAcao=" + dataAcao +
				'}';
	}
}
