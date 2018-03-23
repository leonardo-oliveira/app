package models;

/**
 * Created by William on 09/01/17.
 * Classe java para a
 */

public class RelModeloCheckListImagemEsquema {
	private Integer cod;
	private Integer codModeloCheckList;
	private Integer codImagemEsquemaCheckList;
	private String codigoImagem;
	private String descricaoImagem;
	private Boolean flgAtivo;
	private String dataAcao;
	private Integer codUsuarioClienteResp;
	private Integer codUsuarioResp;

	//Construtor

	public RelModeloCheckListImagemEsquema(Integer cod, Integer codModeloCheckList, Integer codImagemEsquemaCheckList, String codigoImagem, String descricaoImagem, Boolean flgAtivo, String dataAcao, Integer codUsuarioClienteResp, Integer codUsuarioResp) {
		this.cod = cod;
		this.codModeloCheckList = codModeloCheckList;
		this.codImagemEsquemaCheckList = codImagemEsquemaCheckList;
		this.codigoImagem = codigoImagem;
		this.descricaoImagem = descricaoImagem;
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

	public Integer getCodImagemEsquemaCheckList() {
		return codImagemEsquemaCheckList;
	}

	public void setCodImagemEsquemaCheckList(Integer codImagemEsquemaCheckList) {
		this.codImagemEsquemaCheckList = codImagemEsquemaCheckList;
	}

	public String getCodigoImagem() {
		return codigoImagem;
	}

	public void setCodigoImagem(String codigoImagem) {
		this.codigoImagem = codigoImagem;
	}

	public String getDescricaoImagem() {
		return descricaoImagem;
	}

	public void setDescricaoImagem(String descricaoImagem) {
		this.descricaoImagem = descricaoImagem;
	}

	public Boolean getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(Boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
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

	@Override
	public String toString() {
		return "{\"RelModeloCheckListImagemEsquema\":{"
				+ "\"cod\":\"" + cod + "\""
				+ ", \"codModeloCheckList\":\"" + codModeloCheckList + "\""
				+ ", \"codImagemEsquemaCheckList\":\"" + codImagemEsquemaCheckList + "\""
				+ ", \"codigoImagem\":\"" + codigoImagem + "\""
				+ ", \"descricaoImagem\":\"" + descricaoImagem + "\""
				+ ", \"flgAtivo\":\"" + flgAtivo + "\""
				+ ", \"dataAcao\":" + dataAcao
				+ ", \"codUsuarioClienteResp\":\"" + codUsuarioClienteResp + "\""
				+ ", \"codUsuarioResp\":\"" + codUsuarioResp + "\""
				+ "}}";
	}
}

