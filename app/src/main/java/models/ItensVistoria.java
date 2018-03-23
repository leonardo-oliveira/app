package models;

/**
 * Created by William on 06/01/17.
 * Classe java para a
 */

public class ItensVistoria {
    private Integer cod;
    private String codigoItem;
    private String nomeItem;
    private Integer codLocalizacaoItem;
    private Integer codGrupoItem;
    private Boolean flgAtivo;
    private String dataCadastro;
    private Integer codUsuarioClienteResp;
    private Integer codUsuarioResp;
    private String dataAcao;

    //Construtor
    public ItensVistoria(Integer cod, String codigoItem, String nomeItem, Integer codLocalizacaoItem, Integer codGrupoItem, Boolean flgAtivo, String dataCadastro, Integer codUsuarioClienteResp, Integer codUsuarioResp, String dataAcao) {
        this.cod = cod;
        this.codigoItem = codigoItem;
        this.nomeItem = nomeItem;
        this.codLocalizacaoItem = codLocalizacaoItem;
        this.codGrupoItem = codGrupoItem;
        this.flgAtivo = flgAtivo;
        this.dataCadastro = dataCadastro;
        this.codUsuarioClienteResp = codUsuarioClienteResp;
        this.codUsuarioResp = codUsuarioResp;
        this.dataAcao = dataAcao;
    }

    //Getters

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public Integer getCodLocalizacaoItem() {
        return codLocalizacaoItem;
    }

    public void setCodLocalizacaoItem(Integer codLocalizacaoItem) {
        this.codLocalizacaoItem = codLocalizacaoItem;
    }

    public Integer getCodGrupoItem() {
        return codGrupoItem;
    }

    public void setCodGrupoItem(Integer codGrupoItem) {
        this.codGrupoItem = codGrupoItem;
    }

    //Setters

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

    public String getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(String dataAcao) {
        this.dataAcao = dataAcao;
    }

    public void setFlgAtivo(Boolean flgAtivo) {
        this.flgAtivo = flgAtivo;
    }

    @Override
    public String toString() {
        return "{\"ItensVistoria\":{"
                + "\"cod\":\"" + cod + "\""
                + ", \"codigoItem\":\"" + codigoItem + "\""
                + ", \"nomeItem\":\"" + nomeItem + "\""
                + ", \"codLocalizacaoItem\":\"" + codLocalizacaoItem + "\""
                + ", \"codGrupoItem\":\"" + codGrupoItem + "\""
                + ", \"flgAtivo\":\"" + flgAtivo + "\""
                + ", \"dataCadastro\":\"" + dataCadastro + "\""
                + ", \"codUsuarioClienteResp\":\"" + codUsuarioClienteResp + "\""
                + ", \"codUsuarioResp\":\"" + codUsuarioResp + "\""
                + ", \"dataAcao\":\"" + dataAcao + "\""
                + "}}";
    }
}
