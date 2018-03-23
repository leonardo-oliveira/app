package utility;

import java.util.ArrayList;

import models.ModelosCheckList;

/**
 * Created by William on 08/03/17.
 * Classe java para a
 */
public class ModelosCheckListResp {
	public ArrayList<ModelosCheckList> modelosList;


	@Override
	public String toString() {
		return "{\"ModelosCheckListResp\":{"
				+ "\"modelosList\":" + modelosList
				+ "}}";
	}
}
