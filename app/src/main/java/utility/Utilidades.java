package utility;


import android.annotation.SuppressLint;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by William on 04/01/17.
 * Classe java para a
 */

public class Utilidades {
    /*
     * Classe para metodos genericos
     * TODO: Metodos de conexões serião interessantes
    */

	/**
	 * Round to certain number of decimals
	 *
	 * @param d
	 * @param decimalPlace
	 * @return
	 */
	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	/**
	 * Função para pegar a data atual formatando ela da forma que precisar, passando o
	 * formato como parametro
	 *
	 * @param formato
	 * @return
	 */
	public static String getDataHora(String formato) {
		@SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		Date data = new Date();
		Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();
        return dateFormat.format(data_atual);
    }

    public static String conexao() {
        /*
         * TODO: Metodo generico para conexões
        */
	    return "true";
    }

	/**
	 * Função para formatar datas do bd para o formato brasileiro
	 * @param data
	 * @return
	 */
	public static String formatar(String data) {
		try {
			if(data == null)
				return "--";
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			java.util.Date temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(data);

			return sdf1.format(temp);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}

}
