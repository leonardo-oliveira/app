package br.com.technolog.checklist;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by William on 25/05/17.
 * Classe java para a
 */

public class MyValueFormatter implements IAxisValueFormatter {


	/**
	 * Called when a value from an axis is to be formatted
	 * before being drawn. For performance reasons, avoid excessive calculations
	 * and memory allocations inside this method.
	 *
	 * @param value the value to be formatted
	 * @param axis  the axis the value belongs to
	 * @return
	 */
	@Override
	public String getFormattedValue(float value, AxisBase axis) {
		String string = String.valueOf(value);
		return string;
	}
}
