package models;

import utility.Utilidades;

/**
 * Created by William on 02/02/17.
 * Classe java para a
 */

public class ItensCheckList {

	private Integer codItemVistoria;
	private Boolean flgOpcaoOK;
	private Boolean flgOpcaoNaoOK;
	private Boolean flgOpcaoNaoEfet;
	private Boolean flgOpcaoAnexo;
	private Boolean flgOpcaoObs;
	private Integer ordemSequenciaVistoria;
	private String nomeItem;
	private Integer codGrupoItem;
	private Integer codLocalizacaoItem;
	private String mensagensGerais;
	private Boolean opcaoOK;
	private Boolean opcaoNaoOK;
	private Boolean opcaoNaoEfet;
	private String opcaoAnexo;
	private String opcaoObs;
	private Boolean opcaoTroca;
	private String dataAcao = Utilidades.getDataHora("yyyy-MM-dd HH:mm:ss");

	public String getDataAcao() {
		return dataAcao;
	}

	public void setDataAcao(String dataAcao) {
		this.dataAcao = dataAcao;
	}

	public Integer getCodItemVistoria() {
		return codItemVistoria;
	}

	public void setCodItemVistoria(Integer codItemVistoria) {
		this.codItemVistoria = codItemVistoria;
	}

	public Boolean getFlgOpcaoOK() {
		return flgOpcaoOK;
	}

	public void setFlgOpcaoOK(Boolean flgOpcaoOK) {
		this.flgOpcaoOK = flgOpcaoOK;
	}

	public Boolean getFlgOpcaoNaoOK() {
		return flgOpcaoNaoOK;
	}

	public void setFlgOpcaoNaoOK(Boolean flgOpcaoNaoOK) {
		this.flgOpcaoNaoOK = flgOpcaoNaoOK;
	}

	public Boolean getFlgOpcaoNaoEfet() {
		return flgOpcaoNaoEfet;
	}

	public void setFlgOpcaoNaoEfet(Boolean flgOpcaoNaoEfet) {
		this.flgOpcaoNaoEfet = flgOpcaoNaoEfet;
	}

	public Boolean getFlgOpcaoAnexo() {
		return flgOpcaoAnexo;
	}

	public void setFlgOpcaoAnexo(Boolean flgOpcaoAnexo) {
		this.flgOpcaoAnexo = flgOpcaoAnexo;
	}

	public Boolean getFlgOpcaoObs() {
		return flgOpcaoObs;
	}

	public void setFlgOpcaoObs(Boolean flgOpcaoObs) {
		this.flgOpcaoObs = flgOpcaoObs;
	}

	public Integer getOrdemSequenciaVistoria() {
		return ordemSequenciaVistoria;
	}

	public void setOrdemSequenciaVistoria(Integer ordemSequenciaVistoria) {
		this.ordemSequenciaVistoria = ordemSequenciaVistoria;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public Integer getCodGrupoItem() {
		return codGrupoItem;
	}

	public void setCodGrupoItem(Integer codGrupoItem) {
		this.codGrupoItem = codGrupoItem;
	}

	public Integer getCodLocalizacaoItem() {
		return codLocalizacaoItem;
	}

	public void setCodLocalizacaoItem(Integer codLocalizacaoItem) {
		this.codLocalizacaoItem = codLocalizacaoItem;
	}


	public String getMensagensGerais() {
		return mensagensGerais;
	}

	public void setMensagensGerais(String mensagensGerais) {
		this.mensagensGerais = mensagensGerais;
	}

	public Boolean getOpcaoOK() {
		if (opcaoOK == null)
			return false;
		return opcaoOK;
	}

	public void setOpcaoOK(Boolean opcaoOK) {
		if (opcaoOK) {
			this.opcaoOK = true;
			this.opcaoNaoOK = false;
			this.opcaoNaoEfet = false;
		} else {
			this.opcaoOK = null;
		}
	}

	public Boolean getOpcaoNaoOK() {
		if (opcaoNaoOK == null)
			return false;
		return opcaoNaoOK;
	}

	public void setOpcaoNaoOK(Boolean opcaoNaoOK) {
		if (opcaoNaoOK) {
			this.opcaoOK = false;
			this.opcaoNaoOK = true;
			this.opcaoNaoEfet = false;
		} else {
			this.opcaoNaoOK = null;
		}
	}

	public Boolean getOpcaoNaoEfet() {
		if (opcaoNaoEfet == null)
			return false;
		return opcaoNaoEfet;
	}

	public void setOpcaoNaoEfet(Boolean opcaoNaoEfet) {
		if (opcaoNaoEfet) {
			this.opcaoOK = false;
			this.opcaoNaoOK = false;
			this.opcaoNaoEfet = true;
		} else {
			this.opcaoNaoEfet = null;
		}
	}

	public String getOpcaoAnexo() {
		return opcaoAnexo;
	}

	public void setOpcaoAnexo(String opcaoAnexo) {
		this.opcaoAnexo = opcaoAnexo;
	}

	public String getOpcaoObs() {
		return opcaoObs;
	}

	public void setOpcaoObs(String opcaoObs) {
		this.opcaoObs = opcaoObs;
	}

	public boolean getCampoSelecionado() {
		if (getOpcaoOK()) {
			return true;
		} else if (getOpcaoNaoOK()) {
			return true;
		} else if (getOpcaoNaoEfet()) {
			return true;
		} else if (!getFlgOpcaoOK() & !getFlgOpcaoNaoEfet() & !getFlgOpcaoNaoOK()) {
			return true;
		}
		return false;
	}


	public Boolean getOpcaoTroca() {
		return opcaoTroca;
	}

	public void setOpcaoTroca(Boolean opcaoTroca) {
		this.opcaoTroca = opcaoTroca;
	}

	@Override
	public String toString() {
		return "{\"ItensCheckList\":{"
				+ "\"codGrupoItem\":\"" + codGrupoItem + "\""
				+ ", \"codItemVistoria\":\"" + codItemVistoria + "\""
				+ ", \"flgOpcaoOK\":\"" + flgOpcaoOK + "\""
				+ ", \"flgOpcaoNaoOK\":\"" + flgOpcaoNaoOK + "\""
				+ ", \"flgOpcaoNaoEfet\":\"" + flgOpcaoNaoEfet + "\""
				+ ", \"flgOpcaoAnexo\":\"" + flgOpcaoAnexo + "\""
				+ ", \"flgOpcaoObs\":\"" + flgOpcaoObs + "\""
				+ ", \"ordemSequenciaVistoria\":\"" + ordemSequenciaVistoria + "\""
				+ ", \"nomeItem\":\"" + nomeItem + "\""
				+ ", \"codLocalizacaoItem\":\"" + codLocalizacaoItem + "\""
				+ ", \"mensagensGerais\":\"" + mensagensGerais + "\""
				+ ", \"opcaoOK\":\"" + opcaoOK + "\""
				+ ", \"opcaoNaoOK\":\"" + opcaoNaoOK + "\""
				+ ", \"opcaoNaoEfet\":\"" + opcaoNaoEfet + "\""
				+ ", \"opcaoAnexo\":\"" + opcaoAnexo + "\""
				+ ", \"opcaoObs\":\"" + opcaoObs + "\""
				+ ", \"dataAcao\":\"" + dataAcao + "\""
				+ "}}";
	}
}
