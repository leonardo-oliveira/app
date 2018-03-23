package models;

/**
 * Created by William on 09/01/17.
 * Classe java para a
 */

public class RelPermissaoPessoasApoioCheckListVeiculos {
	private Integer cod;
	private Integer codPessoaApoio;
	private Integer codUsuarioPessoaApoio;
	private Integer codVeiculoW;
	private Integer codVeiculo;
	private Boolean flgAtivo;
	private Integer codUsuarioClienteResp;
	private Integer codUsuarioResp;
	private String dataAcao;

	//Construtor

	public RelPermissaoPessoasApoioCheckListVeiculos(Integer cod, Integer codPessoaApoio, Integer codUsuarioPessoaApoio, Integer codVeiculoW, Integer codVeiculo, Boolean flgAtivo, Integer codUsuarioClienteResp, Integer codUsuarioResp, String dataAcao) {
		this.cod = cod;
		this.codPessoaApoio = codPessoaApoio;
		this.codUsuarioPessoaApoio = codUsuarioPessoaApoio;
		this.codVeiculoW = codVeiculoW;
		this.codVeiculo = codVeiculo;
		this.flgAtivo = flgAtivo;
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

	public Integer getCodUsuarioPessoaApoio() {
		return codUsuarioPessoaApoio;
	}

	public void setCodUsuarioPessoaApoio(Integer codUsuarioPessoaApoio) {
		this.codUsuarioPessoaApoio = codUsuarioPessoaApoio;
	}

	public Integer getCodVeiculoW() {
		return codVeiculoW;
	}

	public void setCodVeiculoW(Integer codVeiculoW) {
		this.codVeiculoW = codVeiculoW;
	}

	public Integer getCodVeiculo() {
		return codVeiculo;
	}

	//Setter

	public void setCodVeiculo(Integer codVeiculo) {
		this.codVeiculo = codVeiculo;
	}

	public Boolean getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(Boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
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


}
