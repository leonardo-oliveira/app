package models;

import static utility.Utilidades.formatar;

/**
 * Created by William on 06/01/17.
 * Classe java para a
 */

public class CheckLists {
    private Integer cod;
    private Integer codVeiculo;
    private Integer codModeloCheckList;
    private Integer codVeiculoW;
    private String placaVeiculo;
    private Integer tipoSelecaoVeiculo;
    private String placasCarretas;
	private Boolean flgSomenteReboques;
	private String rfidCarretas;
    private String identificadorDigitalUltimoMotoristaVeiculo;
    private String identificadorDigitalMotoristaAtual;
    private String identificadorDigitalPessoaApoioAtual;
    private String identificadorDigitalMotoristaVistoria;
    private Integer codUsuarioClienteVistoria;
    private Integer codUsuarioApoioVistoria;
    private Integer codUsuarioTechnologVistoria;
	private String dataInicioVistoria;
	private String dataFimVistoria;
	private String dataStatusPreenchimento;
	private Integer statusPreenchimento;
    private Integer statusCheckList;
    private String comentarios;
    private String numeroConhecimentoTransporte;
    private String numeroManifesto;
    private String identificadorSistemasTerceiros;
    private Double volumeCombustivelTanquesInicioVistoria;
    private Double statusTanquesVeiculoInicioVistoria;
    private Double kmVeiculoInicioVistoria;
    private String latitudeVeiculoInicioVistoria;
    private String longitudeVeiculoInicioVistoria;
    private Double volumeCombustivelTanquesFimVistoria;
    private Double statusTanquesVeiculoFimVistoria;
    private Double kmVeiculoFimVistoria;
    private String latitudeVeiculoFimVistoria;
    private String longitudeVeiculoFimVistoria;
    private String latitudeDispositivoCheckList;
    private String longitudeDispositivoCheckList;
    private String caminhoArquivoImagemAssinaturaExecutor;
    private String caminhoArquivoFotoPlacaVeiculo;
	private Boolean flgAtivo;
	private Integer codUsuarioClienteResp;
    private Integer codUsuarioResp;
	private String dataAcao;
	private Integer tipoPreenchimento;
	private String nomeCheck;
	//private Double kmAtual;


	//Getters
	//public Double getKmAtual(){
//		return kmAtual;
//	}
//
//	public void  setKmAtual(Double kmAtual){
//		this.kmAtual = kmAtual;
//	}

    public Integer getCod() {
        return cod;
    }

	public void setCod(Integer cod) {
		this.cod = cod;
	}

    public Integer getCodVeiculo() {
        return codVeiculo;
    }

	public void setCodVeiculo(Integer codVeiculo) {
		this.codVeiculo = codVeiculo;
	}

    public Integer getCodModeloCheckList() {
        return codModeloCheckList;
    }

	public void setCodModeloCheckList(Integer codModeloCheckList) {

		this.codModeloCheckList = codModeloCheckList;
	}

	public String getNomeCheck() {
		return nomeCheck;
	}

	public void setNomecheck(String nomeCheck) {
		this.nomeCheck = nomeCheck;
	}

    public Integer getCodVeiculoW() {
        return codVeiculoW;
    }

	public void setCodVeiculoW(Integer codVeiculoW) {
		this.codVeiculoW = codVeiculoW;
	}

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

    public Integer getTipoSelecaoVeiculo() {
        return tipoSelecaoVeiculo;
    }

	public void setTipoSelecaoVeiculo(Integer tipoSelecaoVeiculo) {
		this.tipoSelecaoVeiculo = tipoSelecaoVeiculo;
	}

    public String getPlacasCarretas() {
        return placasCarretas;
    }

	public void setPlacasCarretas(String placasCarretas) {
		this.placasCarretas = placasCarretas;
	}

	public Boolean isFlgSomenteReboques() {
		return flgSomenteReboques;
    }

    public String getRfidCarretas() {
        return rfidCarretas;
    }

	public void setRfidCarretas(String rfidCarretas) {
		this.rfidCarretas = rfidCarretas;
	}

    public String getIdentificadorDigitalUltimoMotoristaVeiculo() {
        return identificadorDigitalUltimoMotoristaVeiculo;
    }

	public void setIdentificadorDigitalUltimoMotoristaVeiculo(String identificadorDigitalUltimoMotoristaVeiculo) {
		this.identificadorDigitalUltimoMotoristaVeiculo = identificadorDigitalUltimoMotoristaVeiculo;
	}

    public String getIdentificadorDigitalMotoristaAtual() {
        return identificadorDigitalMotoristaAtual;
    }

	public void setIdentificadorDigitalMotoristaAtual(String identificadorDigitalMotoristaAtual) {
		this.identificadorDigitalMotoristaAtual = identificadorDigitalMotoristaAtual;
	}

    public String getIdentificadorDigitalPessoaApoioAtual() {
        return identificadorDigitalPessoaApoioAtual;
    }

	public void setIdentificadorDigitalPessoaApoioAtual(String identificadorDigitalPessoaApoioAtual) {
		this.identificadorDigitalPessoaApoioAtual = identificadorDigitalPessoaApoioAtual;
	}

    public String getIdentificadorDigitalMotoristaVistoria() {
        return identificadorDigitalMotoristaVistoria;
    }

	public void setIdentificadorDigitalMotoristaVistoria(String identificadorDigitalMotoristaVistoria) {
		this.identificadorDigitalMotoristaVistoria = identificadorDigitalMotoristaVistoria;
	}

    public Integer getCodUsuarioClienteVistoria() {
        return codUsuarioClienteVistoria;
    }

	public void setCodUsuarioClienteVistoria(Integer codUsuarioClienteVistoria) {
		this.codUsuarioClienteVistoria = codUsuarioClienteVistoria;
	}

    public Integer getCodUsuarioApoioVistoria() {
        return codUsuarioApoioVistoria;
    }

	public void setCodUsuarioApoioVistoria(Integer codUsuarioApoioVistoria) {
		this.codUsuarioApoioVistoria = codUsuarioApoioVistoria;
	}

    public Integer getCodUsuarioTechnologVistoria() {
        return codUsuarioTechnologVistoria;
    }

	public void setCodUsuarioTechnologVistoria(Integer codUsuarioTechnologVistoria) {
		this.codUsuarioTechnologVistoria = codUsuarioTechnologVistoria;
	}

	public String getDataInicioVistoria() {
		return dataInicioVistoria;
    }

	public void setDataInicioVistoria(String dataInicioVistoria) {
		this.dataInicioVistoria = dataInicioVistoria;
	}

	public String getDataFimVistoria() {
		return dataFimVistoria;
    }

	public void setDataFimVistoria(String dataFimVistoria) {
		this.dataFimVistoria = dataFimVistoria;
	}

	public String getDataStatusPreenchimento() {
		return dataStatusPreenchimento;
    }

	public void setDataStatusPreenchimento(String dataStatusPreenchimento) {
		this.dataStatusPreenchimento = dataStatusPreenchimento;
	}

    public Integer getStatusPreenchimento() {
        return statusPreenchimento;
    }

	public void setStatusPreenchimento(Integer statusPreenchimento) {
		this.statusPreenchimento = statusPreenchimento;
	}

    public Integer getStatusCheckList() {
        return statusCheckList;
    }

	public void setStatusCheckList(Integer statusCheckList) {
		this.statusCheckList = statusCheckList;
	}

    public String getComentarios() {
        return comentarios;
    }

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

    public String getNumeroConhecimentoTransporte() {
        return numeroConhecimentoTransporte;
    }

	//Setters

	public void setNumeroConhecimentoTransporte(String numeroConhecimentoTransporte) {
		this.numeroConhecimentoTransporte = numeroConhecimentoTransporte;
	}

    public String getNumeroManifesto() {
        return numeroManifesto;
    }

	public void setNumeroManifesto(String numeroManifesto) {
		this.numeroManifesto = numeroManifesto;
	}

    public String getIdentificadorSistemasTerceiros() {
        return identificadorSistemasTerceiros;
    }

	public void setIdentificadorSistemasTerceiros(String identificadorSistemasTerceiros) {
		this.identificadorSistemasTerceiros = identificadorSistemasTerceiros;
	}

    public Double getVolumeCombustivelTanquesInicioVistoria() {
        return volumeCombustivelTanquesInicioVistoria;
    }

	public void setVolumeCombustivelTanquesInicioVistoria(Double volumeCombustivelTanquesInicioVistoria) {
		this.volumeCombustivelTanquesInicioVistoria = volumeCombustivelTanquesInicioVistoria;
	}

    public Double getStatusTanquesVeiculoInicioVistoria() {
        return statusTanquesVeiculoInicioVistoria;
    }

	public void setStatusTanquesVeiculoInicioVistoria(Double statusTanquesVeiculoInicioVistoria) {
		this.statusTanquesVeiculoInicioVistoria = statusTanquesVeiculoInicioVistoria;
	}

    public Double getKmVeiculoInicioVistoria() {
        return kmVeiculoInicioVistoria;
    }

	public void setKmVeiculoInicioVistoria(Double kmVeiculoInicioVistoria) {
		this.kmVeiculoInicioVistoria = kmVeiculoInicioVistoria;
	}

    public String getLatitudeVeiculoInicioVistoria() {
        return latitudeVeiculoInicioVistoria;
    }

	public void setLatitudeVeiculoInicioVistoria(String latitudeVeiculoInicioVistoria) {
		this.latitudeVeiculoInicioVistoria = latitudeVeiculoInicioVistoria;
	}

    public String getLongitudeVeiculoInicioVistoria() {
        return longitudeVeiculoInicioVistoria;
    }

	public void setLongitudeVeiculoInicioVistoria(String longitudeVeiculoInicioVistoria) {
		this.longitudeVeiculoInicioVistoria = longitudeVeiculoInicioVistoria;
	}

    public Double getVolumeCombustivelTanquesFimVistoria() {
        return volumeCombustivelTanquesFimVistoria;
    }

	public void setVolumeCombustivelTanquesFimVistoria(Double volumeCombustivelTanquesFimVistoria) {
		this.volumeCombustivelTanquesFimVistoria = volumeCombustivelTanquesFimVistoria;
	}

    public Double getStatusTanquesVeiculoFimVistoria() {
        return statusTanquesVeiculoFimVistoria;
    }

	public void setStatusTanquesVeiculoFimVistoria(Double statusTanquesVeiculoFimVistoria) {
		this.statusTanquesVeiculoFimVistoria = statusTanquesVeiculoFimVistoria;
	}

    public Double getKmVeiculoFimVistoria() {
        return kmVeiculoFimVistoria;
    }

	public void setKmVeiculoFimVistoria(Double kmVeiculoFimVistoria) {
		this.kmVeiculoFimVistoria = kmVeiculoFimVistoria;
	}

    public String getLatitudeVeiculoFimVistoria() {
        return latitudeVeiculoFimVistoria;
    }

	public void setLatitudeVeiculoFimVistoria(String latitudeVeiculoFimVistoria) {
		this.latitudeVeiculoFimVistoria = latitudeVeiculoFimVistoria;
	}

    public String getLongitudeVeiculoFimVistoria() {
        return longitudeVeiculoFimVistoria;
    }

	public void setLongitudeVeiculoFimVistoria(String longitudeVeiculoFimVistoria) {
		this.longitudeVeiculoFimVistoria = longitudeVeiculoFimVistoria;
	}

    public String getLatitudeDispositivoCheckList() {
        return latitudeDispositivoCheckList;
    }

	public void setLatitudeDispositivoCheckList(String latitudeDispositivoCheckList) {
		this.latitudeDispositivoCheckList = latitudeDispositivoCheckList;
	}

    public String getLongitudeDispositivoCheckList() {
        return longitudeDispositivoCheckList;
    }

	public void setLongitudeDispositivoCheckList(String longitudeDispositivoCheckList) {
		this.longitudeDispositivoCheckList = longitudeDispositivoCheckList;
	}

    public String getCaminhoArquivoImagemAssinaturaExecutor() {
        return caminhoArquivoImagemAssinaturaExecutor;
    }

	public void setCaminhoArquivoImagemAssinaturaExecutor(String caminhoArquivoImagemAssinaturaExecutor) {
		this.caminhoArquivoImagemAssinaturaExecutor = caminhoArquivoImagemAssinaturaExecutor;
	}

    public String getCaminhoArquivoFotoPlacaVeiculo() {
        return caminhoArquivoFotoPlacaVeiculo;
    }

	public void setCaminhoArquivoFotoPlacaVeiculo(String caminhoArquivoFotoPlacaVeiculo) {
		this.caminhoArquivoFotoPlacaVeiculo = caminhoArquivoFotoPlacaVeiculo;
	}

	public Boolean isFlgAtivo() {
		return flgAtivo;
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

	public Integer getTipoPreenchimento() {
		return tipoPreenchimento;
	}

	public void setTipoPreenchimento(Integer tipoPreenchimento) {
		this.tipoPreenchimento = tipoPreenchimento;
	}

	public void setFlgAtivo(Boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
    }

	public CheckLists Converter() {

		return this;
	}

	public Boolean getFlgSomenteReboques() {
		return flgSomenteReboques;
	}

	public void setFlgSomenteReboques(Boolean flgSomenteReboques) {
		this.flgSomenteReboques = flgSomenteReboques;
	}

	public String toStringInfos() {
		return nomeCheck + " " + placaVeiculo + " " + formatar(dataStatusPreenchimento);
	}

	@Override
	public String toString() {
		return "{\"CheckLists\":{"
				+ "\"caminhoArquivoFotoPlacaVeiculo\":\"" + caminhoArquivoFotoPlacaVeiculo + "\""
				+ ", \"cod\":\"" + cod + "\""
				+ ", \"codVeiculo\":\"" + codVeiculo + "\""
				+ ", \"codModeloCheckList\":\"" + codModeloCheckList + "\""
				+ ", \"codVeiculoW\":\"" + codVeiculoW + "\""
				+ ", \"placaVeiculo\":\"" + placaVeiculo + "\""
				+ ", \"tipoSelecaoVeiculo\":\"" + tipoSelecaoVeiculo + "\""
				+ ", \"placasCarretas\":\"" + placasCarretas + "\""
				+ ", \"flgSomenteReboques\":\"" + flgSomenteReboques + "\""
				+ ", \"rfidCarretas\":\"" + rfidCarretas + "\""
				+ ", \"identificadorDigitalUltimoMotoristaVeiculo\":\"" + identificadorDigitalUltimoMotoristaVeiculo + "\""
				+ ", \"identificadorDigitalMotoristaAtual\":\"" + identificadorDigitalMotoristaAtual + "\""
				+ ", \"identificadorDigitalPessoaApoioAtual\":\"" + identificadorDigitalPessoaApoioAtual + "\""
				+ ", \"identificadorDigitalMotoristaVistoria\":\"" + identificadorDigitalMotoristaVistoria + "\""
				+ ", \"codUsuarioClienteVistoria\":\"" + codUsuarioClienteVistoria + "\""
				+ ", \"codUsuarioApoioVistoria\":\"" + codUsuarioApoioVistoria + "\""
				+ ", \"codUsuarioTechnologVistoria\":\"" + codUsuarioTechnologVistoria + "\""
				+ ", \"dataInicioVistoria\":\"" + dataInicioVistoria + "\""
				+ ", \"dataFimVistoria\":\"" + dataFimVistoria + "\""
				+ ", \"dataStatusPreenchimento\":\"" + dataStatusPreenchimento + "\""
				+ ", \"statusPreenchimento\":\"" + statusPreenchimento + "\""
				+ ", \"statusCheckList\":\"" + statusCheckList + "\""
				+ ", \"comentarios\":\"" + comentarios + "\""
				+ ", \"numeroConhecimentoTransporte\":\"" + numeroConhecimentoTransporte + "\""
				+ ", \"numeroManifesto\":\"" + numeroManifesto + "\""
				+ ", \"identificadorSistemasTerceiros\":\"" + identificadorSistemasTerceiros + "\""
				+ ", \"volumeCombustivelTanquesInicioVistoria\":\"" + volumeCombustivelTanquesInicioVistoria + "\""
				+ ", \"statusTanquesVeiculoInicioVistoria\":\"" + statusTanquesVeiculoInicioVistoria + "\""
				+ ", \"kmVeiculoInicioVistoria\":\"" + kmVeiculoInicioVistoria + "\""
				+ ", \"latitudeVeiculoInicioVistoria\":\"" + latitudeVeiculoInicioVistoria + "\""
				+ ", \"longitudeVeiculoInicioVistoria\":\"" + longitudeVeiculoInicioVistoria + "\""
				+ ", \"volumeCombustivelTanquesFimVistoria\":\"" + volumeCombustivelTanquesFimVistoria + "\""
				+ ", \"statusTanquesVeiculoFimVistoria\":\"" + statusTanquesVeiculoFimVistoria + "\""
				+ ", \"kmVeiculoFimVistoria\":\"" + kmVeiculoFimVistoria + "\""
				+ ", \"latitudeVeiculoFimVistoria\":\"" + latitudeVeiculoFimVistoria + "\""
				+ ", \"longitudeVeiculoFimVistoria\":\"" + longitudeVeiculoFimVistoria + "\""
				+ ", \"latitudeDispositivoCheckList\":\"" + latitudeDispositivoCheckList + "\""
				+ ", \"longitudeDispositivoCheckList\":\"" + longitudeDispositivoCheckList + "\""
				+ ", \"caminhoArquivoImagemAssinaturaExecutor\":\"" + caminhoArquivoImagemAssinaturaExecutor + "\""
				+ ", \"flgAtivo\":\"" + flgAtivo + "\""
				+ ", \"codUsuarioClienteResp\":\"" + codUsuarioClienteResp + "\""
				+ ", \"codUsuarioResp\":\"" + codUsuarioResp + "\""
				+ ", \"dataAcao\":\"" + dataAcao + "\""
				+ ", \"tipoPreenchimento\":\"" + tipoPreenchimento + "\""
				+ ", \"nomeCheck\":\"" + nomeCheck + "\""
				+ "}}";
	}
}
