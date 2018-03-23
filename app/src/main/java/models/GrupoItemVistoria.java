package models;

/**
 * Created by William on 06/01/17.
 * Classe java para a
 */

public class GrupoItemVistoria {
    private Integer cod;
    private String nome;
    private Boolean flgGrupoCavalo;
    private Boolean flgGrupoReboque;
    private Boolean flgAtivo;
    private String dataCadastro;
    private String dataAcao;
    private Integer codUsuarioClienteResp;
    private Integer codUsuarioResp;

    public GrupoItemVistoria() {
    }

//Getters

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Boolean isFlgGrupoCavalo() {
        return flgGrupoCavalo;
    }

    public Boolean isFlgGrupoReboque() {
        return flgGrupoReboque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    //Setters

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

    public void setFlgGrupoCavalo(Boolean flgGrupoCavalo) {
        this.flgGrupoCavalo = flgGrupoCavalo;
    }

    public void setFlgGrupoReboque(Boolean flgGrupoReboque) {
        this.flgGrupoReboque = flgGrupoReboque;
    }

    public void setFlgAtivo(Boolean flgAtivo) {
        this.flgAtivo = flgAtivo;
    }
}
