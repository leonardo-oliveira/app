package utility;

import java.util.ArrayList;

import models.Veiculos;

/**
 * Created by William on 09/03/17.
 * Classe java para a
 */
public class ListVeic {
	public ArrayList<Veiculos> veiculosList;


	@Override
	public String toString() {
		return "{\"ListVeic\":{"
				+ "\"veiculosList\":" + veiculosList
				+ "}}";
	}
}
