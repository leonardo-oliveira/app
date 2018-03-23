package models;

/**
 * Created by William on 09/01/17.
 * Classe java para a
 */

public class RelModeloCheckListItensVistoria {
	private Integer cod;
	private Integer codModeloCheckList;
	private Integer codItemVistoria;
	private String codigoItem;
	private Integer codigoGrupoItemVistoria;
	private Integer codigoLocalizacaoItemVistoria;
	private Boolean flgOpcaoOK;
	private Boolean flgOpcaoNaoOk;
	private Boolean flgOpcaoNaoEfetuado;
	private Boolean flgOpcaoObservacao;
	private Boolean flgOpcaoAnexo;
	private Integer ordemSequenciaVistoria;
	private String mensagemExecutorVistoriaItem;
	private Boolean flgAtivo;
	private String dataAcao;
	private Integer codUsuarioClienteResp;
	private Integer codUsuarioResp;

	//Construtor
	public RelModeloCheckListItensVistoria(Integer cod, Integer codModeloCheckList, Integer codItemVistoria, String codigoItem, Integer codigoGrupoItemVistoria, Integer codigoLocalizacaoItemVistoria, Boolean flgOpcaoOK, Boolean flgOpcaoNaoOk, Boolean flgOpcaoNaoEfetuado, Boolean flgOpcaoObservacao, Boolean flgOpcaoAnexo, Integer ordemSequenciaVistoria, String mensagemExecutorVistoriaItem, Boolean flgAtivo, String dataAcao, Integer codUsuarioClienteResp, Integer codUsuarioResp) {
		this.cod = cod;
		this.codModeloCheckList = codModeloCheckList;
		this.codItemVistoria = codItemVistoria;
		this.codigoItem = codigoItem;
		this.codigoGrupoItemVistoria = codigoGrupoItemVistoria;
		this.codigoLocalizacaoItemVistoria = codigoLocalizacaoItemVistoria;
		this.flgOpcaoOK = flgOpcaoOK;
		this.flgOpcaoNaoOk = flgOpcaoNaoOk;
		this.flgOpcaoNaoEfetuado = flgOpcaoNaoEfetuado;
		this.flgOpcaoObservacao = flgOpcaoObservacao;
		this.flgOpcaoAnexo = flgOpcaoAnexo;
		this.ordemSequenciaVistoria = ordemSequenciaVistoria;
		this.mensagemExecutorVistoriaItem = mensagemExecutorVistoriaItem;
		this.flgAtivo = flgAtivo;
		this.dataAcao = dataAcao;
		this.codUsuarioClienteResp = codUsuarioClienteResp;
		this.codUsuarioResp = codUsuarioResp;
	}

	//Getter
	public Integer getCod() {
		return cod;
	}

	//Setter
	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public Integer getCodModeloCheckList() {
		return codModeloCheckList;
	}

	public void setCodModeloCheckList(Integer codModeloCheckList) {
		this.codModeloCheckList = codModeloCheckList;
	}

	public Integer getCodItemVistoria() {
		return codItemVistoria;
	}

	public void setCodItemVistoria(Integer codItemVistoria) {
		this.codItemVistoria = codItemVistoria;
	}

	public String getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}

	public Integer getCodigoGrupoItemVistoria() {
		return codigoGrupoItemVistoria;
	}

	public void setCodigoGrupoItemVistoria(Integer codigoGrupoItemVistoria) {
		this.codigoGrupoItemVistoria = codigoGrupoItemVistoria;
	}

	public Integer getCodigoLocalizacaoItemVistoria() {
		return codigoLocalizacaoItemVistoria;
	}

	public void setCodigoLocalizacaoItemVistoria(Integer codigoLocalizacaoItemVistoria) {
		this.codigoLocalizacaoItemVistoria = codigoLocalizacaoItemVistoria;
	}

	public Boolean isFlgOpcaoOK() {
		return flgOpcaoOK;
	}

	public Boolean isFlgOpcaoNaoOk() {
		return flgOpcaoNaoOk;
	}

	public Boolean isFlgOpcaoNaoEfetuado() {
		return flgOpcaoNaoEfetuado;
	}

	public Boolean isFlgOpcaoObservacao() {
		return flgOpcaoObservacao;
	}

	public Boolean isFlgOpcaoAnexo() {
		return flgOpcaoAnexo;
	}

	public Integer getOrdemSequenciaVistoria() {
		return ordemSequenciaVistoria;
	}

	public void setOrdemSequenciaVistoria(Integer ordemSequenciaVistoria) {
		this.ordemSequenciaVistoria = ordemSequenciaVistoria;
	}

	public String getMensagemExecutorVistoriaItem() {
		return mensagemExecutorVistoriaItem;
	}

	public void setMensagemExecutorVistoriaItem(String mensagemExecutorVistoriaItem) {
		this.mensagemExecutorVistoriaItem = mensagemExecutorVistoriaItem;
	}

	public Boolean isFlgAtivo() {
		return flgAtivo;
	}

	public String getDataAcao() {
		return dataAcao;
	}

	public void setDataAcao(String dataAcao) {
		this.dataAcao = dataAcao;
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

	public void setFlgOpcaoOK(Boolean flgOpcaoOK) {
		this.flgOpcaoOK = flgOpcaoOK;
	}

	public void setFlgOpcaoNaoOk(Boolean flgOpcaoNaoOk) {
		this.flgOpcaoNaoOk = flgOpcaoNaoOk;
	}

	public void setFlgOpcaoNaoEfetuado(Boolean flgOpcaoNaoEfetuado) {
		this.flgOpcaoNaoEfetuado = flgOpcaoNaoEfetuado;
	}

	public void setFlgOpcaoObservacao(Boolean flgOpcaoObservacao) {
		this.flgOpcaoObservacao = flgOpcaoObservacao;
	}

	public void setFlgOpcaoAnexo(Boolean flgOpcaoAnexo) {
		this.flgOpcaoAnexo = flgOpcaoAnexo;
	}

	public void setFlgAtivo(Boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	@Override
	public String toString() {
		return "{\"RelModeloCheckListItensVistoria\":{"
				+ "\"cod\":\"" + cod + "\""
				+ ", \"codModeloCheckList\":\"" + codModeloCheckList + "\""
				+ ", \"codItemVistoria\":\"" + codItemVistoria + "\""
				+ ", \"codigoItem\":\"" + codigoItem + "\""
				+ ", \"codigoGrupoItemVistoria\":\"" + codigoGrupoItemVistoria + "\""
				+ ", \"codigoLocalizacaoItemVistoria\":\"" + codigoLocalizacaoItemVistoria + "\""
				+ ", \"flgOpcaoOK\":\"" + flgOpcaoOK + "\""
				+ ", \"flgOpcaoNaoOk\":\"" + flgOpcaoNaoOk + "\""
				+ ", \"flgOpcaoNaoEfetuado\":\"" + flgOpcaoNaoEfetuado + "\""
				+ ", \"flgOpcaoObservacao\":\"" + flgOpcaoObservacao + "\""
				+ ", \"flgOpcaoAnexo\":\"" + flgOpcaoAnexo + "\""
				+ ", \"ordemSequenciaVistoria\":\"" + ordemSequenciaVistoria + "\""
				+ ", \"mensagemExecutorVistoriaItem\":\"" + mensagemExecutorVistoriaItem + "\""
				+ ", \"flgAtivo\":\"" + flgAtivo + "\""
				+ ", \"dataAcao\":\"" + dataAcao + "\""
				+ ", \"codUsuarioClienteResp\":\"" + codUsuarioClienteResp + "\""
				+ ", \"codUsuarioResp\":\"" + codUsuarioResp + "\""
				+ "}}";
	}
}

