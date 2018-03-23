package models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;

/**
 * Created by William on 09/01/17.
 * Classe java para a
 */

public class RelModeloCheckListVeiculos {
	private Integer cod;
	private Integer codVeiculos;
	private Integer codVeiculoW;
	private String placaVeiculo;
	private Integer codModeloCheckList;
	private Boolean flgAtivo;
	private String dataAcao;
	private Integer codUsuarioClienteResp;
	private Integer codUsuarioResp;

	//construtor
	public RelModeloCheckListVeiculos(Integer cod, Integer codVeiculos, Integer codVeiculoW, String placaVeiculo, Integer codModeloCheckList, Boolean flgAtivo, String dataAcao, Integer codUsuarioClienteResp, Integer codUsuarioResp) {
		this.cod = cod;
		this.codVeiculos = codVeiculos;
		this.codVeiculoW = codVeiculoW;
		this.placaVeiculo = placaVeiculo;
		this.codModeloCheckList = codModeloCheckList;
		this.flgAtivo = flgAtivo;
		this.dataAcao = dataAcao;
		this.codUsuarioClienteResp = codUsuarioClienteResp;
		this.codUsuarioResp = codUsuarioResp;
	}

	public RelModeloCheckListVeiculos() {
	}

	//Getters
	public Integer getCodVeiculos() {
		return codVeiculos;
	}

	public void setCodVeiculos(Integer codVeiculos) {
		this.codVeiculos = codVeiculos;
	}

	public Integer getCod() {
		return cod;
	}

	//Setters
	public void setCod(Integer cod) {
		this.cod = cod;
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

	public Integer getCodModeloCheckList() {
		return codModeloCheckList;
	}

	public void setCodModeloCheckList(Integer codModeloCheckList) {
		this.codModeloCheckList = codModeloCheckList;
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

	public void setFlgAtivo(Boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	@Override
	public String toString() {
		return "{\"RelModeloCheckListVeiculos\":{"
				+ "\"cod\":\"" + cod + "\""
				+ ", \"codVeiculos\":\"" + codVeiculos + "\""
				+ ", \"codVeiculoW\":\"" + codVeiculoW + "\""
				+ ", \"placaVeiculo\":\"" + placaVeiculo + "\""
				+ ", \"codModeloCheckList\":\"" + codModeloCheckList + "\""
				+ ", \"flgAtivo\":\"" + flgAtivo + "\""
				+ ", \"dataAcao\":\"" + dataAcao + "\""
				+ ", \"codUsuarioClienteResp\":\"" + codUsuarioClienteResp + "\""
				+ ", \"codUsuarioResp\":\"" + codUsuarioResp + "\""
				+ "}}";
	}

    /**
     * Created by William on 06/01/17.
     * Classe java para a
     */

    public static class ImagensEsquema {
        private Integer cod;
        private String codigoImagem;
        private String caminhoArquivoImagem;
        private String descricaoImagem;
        private Boolean flgAtivo;
        private String dataCadastro;
        private Integer codUsuarioClienteResp;
        private Integer codUsuarioResp;
        private String dataAcao;
        private String link = "http://solutions.technolog.com.br/";
        //Constutores

        public ImagensEsquema(Integer cod, String codigoImagem, String caminhoArquivoImagem, String descricaoImagem, Boolean flgAtivo, String dataCadastro, Integer codUsuarioClienteResp, Integer codUsuarioResp, String dataAcao) {
            this.cod = cod;
            this.codigoImagem = codigoImagem;
            this.caminhoArquivoImagem = caminhoArquivoImagem;
            this.descricaoImagem = descricaoImagem;
            this.flgAtivo = flgAtivo;
            this.dataCadastro = dataCadastro;
            this.codUsuarioClienteResp = codUsuarioClienteResp;
            this.codUsuarioResp = codUsuarioResp;
            this.dataAcao = dataAcao;
        }

        public ImagensEsquema() {

        }

        //Getters

        public Integer getCod() {
            return cod;
        }

        //Setters
        public void setCod(Integer cod) {
            this.cod = cod;
        }

        public String getCodigoImagem() {
            return codigoImagem;
        }

        public void setCodigoImagem(String codigoImagem) {
            this.codigoImagem = codigoImagem;
        }

        public String getCaminhoArquivoImagem() {
            return caminhoArquivoImagem;
        }

        public void setCaminhoArquivoImagem(String caminhoArquivoImagem) {
            this.caminhoArquivoImagem = caminhoArquivoImagem;
        }

        public String getDescricaoImagem() {
            return descricaoImagem;
        }

        public void setDescricaoImagem(String descricaoImagem) {
            this.descricaoImagem = descricaoImagem;
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

        public String getDataAcao() {
            return dataAcao;
        }

        public void setDataAcao(String dataAcao) {
            this.dataAcao = dataAcao;
        }

        public Boolean getFlgAtivo() {
            return flgAtivo;
        }

        public void setFlgAtivo(Boolean flgAtivo) {
            this.flgAtivo = flgAtivo;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLinkImagem() {
            caminhoArquivoImagem = caminhoArquivoImagem.replace("/var/www/html", "");
            return "http://solutions.technolog.com.br/" + caminhoArquivoImagem;
        }

        public Bitmap getImagem() {
            File image = new File(caminhoArquivoImagem);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();

            return BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
        }

        public Uri getUri() {
            File image = new File(caminhoArquivoImagem);
            return Uri.fromFile(image);
        }

        @Override
        public String toString() {
            return "{\"ImagensEsquema\":{"
                    + "\"cod\":\"" + cod + "\""
                    + ", \"codigoImagem\":\"" + codigoImagem + "\""
                    + ", \"caminhoArquivoImagem\":\"" + caminhoArquivoImagem + "\""
                    + ", \"descricaoImagem\":\"" + descricaoImagem + "\""
                    + ", \"flgAtivo\":\"" + flgAtivo + "\""
                    + ", \"dataCadastro\":\"" + dataCadastro + "\""
                    + ", \"codUsuarioClienteResp\":\"" + codUsuarioClienteResp + "\""
                    + ", \"codUsuarioResp\":\"" + codUsuarioResp + "\""
                    + ", \"dataAcao\":\"" + dataAcao + "\""
                    + ", \"link\":\"" + link + "\""
                    + "}}";
        }
    }
}
