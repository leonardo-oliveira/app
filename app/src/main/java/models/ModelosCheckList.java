package models;

/**
 * Created by William on 09/01/17.
 * Classe java para a
 */

public class ModelosCheckList {
	private Integer cod;
	private String nome;
	private String descricao;
	private String mensagensGeraisExecutorCheckList;
	private String dataAcao;
	private Boolean flgSomenteMotoristaAtualExecuta;
	private Boolean flgSomenteGerentesOperacaoExecutam;
	private Boolean flgSomentePessoaApoioExecutam;
	private Boolean flgTodasPessoasExecutam;
	private Integer tipoDisponibilidadeModelo;
	private Boolean flgAtivo;
	private String dataCadastro;
	private Integer codUsuarioClienteResp;
	private Integer codUsuarioResp;

	//Construtor

	//Getters

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMensagensGeraisExecutorCheckList() {
		return mensagensGeraisExecutorCheckList;
	}

	public void setMensagensGeraisExecutorCheckList(String mensagensGeraisExecutorCheckList) {
		this.mensagensGeraisExecutorCheckList = mensagensGeraisExecutorCheckList;
	}

	public String getDataAcao() {
		return dataAcao;
	}

	public void setDataAcao(String dataAcao) {
		this.dataAcao = dataAcao;
	}

	public Boolean isFlgSomenteMotoristaAtualExecuta() {
		return flgSomenteMotoristaAtualExecuta;
	}

	public Boolean isFlgSomenteGerentesOperacaoExecutam() {
		return flgSomenteGerentesOperacaoExecutam;
	}

	public Boolean isFlgSomentePessoaApoioExecutam() {
		return flgSomentePessoaApoioExecutam;
	}

	public Boolean isFlgTodasPessoasExecutam() {
		return flgTodasPessoasExecutam;
	}

	//Setters

	public Integer getTipoDisponibilidadeModelo() {
		return tipoDisponibilidadeModelo;
	}

	public void setTipoDisponibilidadeModelo(Integer tipoDisponibilidadeModelo) {
		this.tipoDisponibilidadeModelo = tipoDisponibilidadeModelo;
	}

	public Boolean isFlgAtivo() {
		return flgAtivo;
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

	public void setFlgSomenteMotoristaAtualExecuta(Boolean flgSomenteMotoristaAtualExecuta) {
		this.flgSomenteMotoristaAtualExecuta = flgSomenteMotoristaAtualExecuta;
	}

	public void setFlgSomenteGerentesOperacaoExecutam(Boolean flgSomenteGerentesOperacaoExecutam) {
		this.flgSomenteGerentesOperacaoExecutam = flgSomenteGerentesOperacaoExecutam;
	}

	public void setFlgSomentePessoaApoioExecutam(Boolean flgSomentePessoaApoioExecutam) {
		this.flgSomentePessoaApoioExecutam = flgSomentePessoaApoioExecutam;
	}

	public void setFlgTodasPessoasExecutam(Boolean flgTodasPessoasExecutam) {
		this.flgTodasPessoasExecutam = flgTodasPessoasExecutam;
	}

	public void setFlgAtivo(Boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}


	@Override
	public String toString() {
		return "{\"ModelosCheckList\":{"
				+ "\"cod\":\"" + cod + "\""
				+ ", \"nome\":\"" + nome + "\""
				+ ", \"descricao\":\"" + descricao + "\""
				+ ", \"mensagensGeraisExecutorCheckList\":\"" + mensagensGeraisExecutorCheckList + "\""
				+ ", \"dataAcao\":\"" + dataAcao + "\""
				+ ", \"flgSomenteMotoristaAtualExecuta\":\"" + flgSomenteMotoristaAtualExecuta + "\""
				+ ", \"flgSomenteGerentesOperacaoExecutam\":\"" + flgSomenteGerentesOperacaoExecutam + "\""
				+ ", \"flgSomentePessoaApoioExecutam\":\"" + flgSomentePessoaApoioExecutam + "\""
				+ ", \"flgTodasPessoasExecutam\":\"" + flgTodasPessoasExecutam + "\""
				+ ", \"tipoDisponibilidadeModelo\":\"" + tipoDisponibilidadeModelo + "\""
				+ ", \"flgAtivo\":\"" + flgAtivo + "\""
				+ ", \"dataCadastro\":\"" + dataCadastro + "\""
				+ ", \"codUsuarioClienteResp\":\"" + codUsuarioClienteResp + "\""
				+ ", \"codUsuarioResp\":\"" + codUsuarioResp + "\""
				+ "}}";
	}
}


