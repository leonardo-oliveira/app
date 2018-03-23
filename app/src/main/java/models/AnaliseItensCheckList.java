package models;

/**
 * Created by William on 09/01/17.
 * Classe java para a Analise Itens Checklist
 */

public class AnaliseItensCheckList {
	private Integer cod;
	private Integer codCheckList;
	private Integer codItemVistoria;
	private Boolean flgOK;
	private Boolean flgNaoOK;
	private Boolean flgNaoEfetuado;
	private Boolean flgNecessitaTrocaReparo;
	private String observacaoExecutor;
	private String arquivoAnexoCheckList;
	private Boolean flgAtivo;
	private String dataAcao;
	private Integer codUsuarioClienteResp;
	private Integer codUsuarioResp;

	//Construtor
	public AnaliseItensCheckList(Integer cod, Integer codCheckList, Integer codItemVistoria, Boolean flgOK, Boolean flgNaoOK, Boolean flgNaoEfetuado, Boolean flgNecessitaTrocaReparo, String observacaoExecutor, String arquivoAnexoCheckList, Boolean flgAtivo, String dataAcao, Integer codUsuarioClienteResp, Integer codUsuarioResp) {
		this.cod = cod;
		this.codCheckList = codCheckList;
		this.codItemVistoria = codItemVistoria;
		this.flgOK = flgOK;
		this.flgNaoOK = flgNaoOK;
		this.flgNaoEfetuado = flgNaoEfetuado;
		this.flgNecessitaTrocaReparo = flgNecessitaTrocaReparo;
		this.observacaoExecutor = observacaoExecutor;
		this.arquivoAnexoCheckList = arquivoAnexoCheckList;
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

	public Integer getCodCheckList() {
		return codCheckList;
	}

	public void setCodCheckList(Integer codCheckList) {
		this.codCheckList = codCheckList;
	}

	public Integer getCodItemVistoria() {
		return codItemVistoria;
	}

	public void setCodItemVistoria(Integer codItemVistoria) {
		this.codItemVistoria = codItemVistoria;
	}

	public Boolean isFlgOK() {
		return flgOK;
	}

	public Boolean isFlgNaoOK() {
		return flgNaoOK;
	}

	public Boolean isFlgNaoEfetuado() {
		return flgNaoEfetuado;
	}

	public Boolean isFlgNecessitaTrocaReparo() {
		return flgNecessitaTrocaReparo;
	}

	public String getObservacaoExecutor() {
		return observacaoExecutor;
	}

	public void setObservacaoExecutor(String observacaoExecutor) {
		this.observacaoExecutor = observacaoExecutor;
	}

	public String getArquivoAnexoCheckList() {
		return arquivoAnexoCheckList;
	}

	public void setArquivoAnexoCheckList(String arquivoAnexoCheckList) {
		this.arquivoAnexoCheckList = arquivoAnexoCheckList;
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

	public void setFlgOK(Boolean flgOK) {
		this.flgOK = flgOK;
	}

	public void setFlgNaoOK(Boolean flgNaoOK) {
		this.flgNaoOK = flgNaoOK;
	}

	public void setFlgNaoEfetuado(Boolean flgNaoEfetuado) {
		this.flgNaoEfetuado = flgNaoEfetuado;
	}

	public void setFlgNecessitaTrocaReparo(Boolean flgNecessitaTrocaReparo) {
		this.flgNecessitaTrocaReparo = flgNecessitaTrocaReparo;
	}

	public void setFlgAtivo(Boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	@Override
	public String toString() {
		return "{\"AnaliseItensCheckList\":{"
				+ "\"cod\":\"" + cod + "\""
				+ ", \"codCheckList\":\"" + codCheckList + "\""
				+ ", \"codItemVistoria\":\"" + codItemVistoria + "\""
				+ ", \"flgOK\":\"" + flgOK + "\""
				+ ", \"flgNaoOK\":\"" + flgNaoOK + "\""
				+ ", \"flgNaoEfetuado\":\"" + flgNaoEfetuado + "\""
				+ ", \"flgNecessitaTrocaReparo\":\"" + flgNecessitaTrocaReparo + "\""
				+ ", \"observacaoExecutor\":\"" + observacaoExecutor + "\""
				+ ", \"arquivoAnexoCheckList\":\"" + arquivoAnexoCheckList + "\""
				+ ", \"flgAtivo\":\"" + flgAtivo + "\""
				+ ", \"dataAcao\":" + dataAcao
				+ ", \"codUsuarioClienteResp\":\"" + codUsuarioClienteResp + "\""
				+ ", \"codUsuarioResp\":\"" + codUsuarioResp + "\""
				+ "}}";
	}

}

