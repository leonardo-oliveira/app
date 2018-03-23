package models;

import java.util.List;

/**
 * Created by William on 24/01/17.
 * Classe java para a
 */

public class ServiceSyncResp {
	public List<ItensVistoria> itensVistoriaList;
	public List<RelModeloCheckListItensVistoria> relModeloCheckListItensVistoriaList;
	public List<RelModeloCheckListVeiculos> relModeloCheckListVeiculosList;
	public List<RelModeloCheckListImagemEsquema> relModeloCheckListImagemEsquemaList;
	public List<GrupoItemVistoria> grupoItemVistoriaList;
	public List<LocalizacaoItemVistoria> localizacaoItemVistoriaList;
	public List<ModelosCheckList> modelosCheckListList;
	public List<RelModeloCheckListVeiculos.ImagensEsquema> imagensEsquemaList;
	public List<Veiculos> veiculosList;
	public List<Usuarios> users;

}
