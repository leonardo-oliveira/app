package models;

/**
 * Created by William on 06/01/17.
 * Classe java para a
 */

public class LocalizacaoItemVistoria {
    private Integer cod;
    private String nome;
    private String caminhoArquivoImagemIlustracao;
    private Boolean flgAtivo;
    private String dataCadastro;
    private Integer codUsuarioClienteResp;
    private Integer codUsuarioResp;
    private String dataAcao;

    //Construtor
    public LocalizacaoItemVistoria(Integer cod, String nome, String caminhoArquivoImagemIlustracao, Boolean flgAtivo, String dataCadastro, Integer codUsuarioClienteResp, Integer codUsuarioResp, String dataAcao) {
        this.cod = cod;
        this.nome = nome;
        this.caminhoArquivoImagemIlustracao = caminhoArquivoImagemIlustracao;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminhoArquivoImagemIlustracao() {
        return caminhoArquivoImagemIlustracao;
    }

    public void setCaminhoArquivoImagemIlustracao(String caminhoArquivoImagemIlustracao) {
        this.caminhoArquivoImagemIlustracao = caminhoArquivoImagemIlustracao;
    }

    public Boolean isFlgAtivo() {
        return flgAtivo;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    //Setters

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
}