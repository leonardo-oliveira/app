package models;

/**
 * Created by William on 17/02/17.
 * Classe java para a
 */

public class Result {
	private String result;
	private String value;
	private String teste;
	private String check;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "{\"Result\":{"
				+ "\"check\":\"" + check + "\""
				+ ", \"result\":\"" + result + "\""
				+ ", \"value\":\"" + value + "\""
				+ ", \"teste\":\"" + teste + "\""
				+ "}}";
	}
}
