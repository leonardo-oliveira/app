package models;

/**
 * Created by William on 21/06/17.
 * Classe java para a
 */

public class RPM {
	private Integer rpm;
	private String utcDateTime;

	public Integer getRpm() {
		if(rpm == null)
			return 0;
		return rpm;
	}

	public void setRpm(Integer rpm) {
		this.rpm = rpm;
	}

	public String getUtcDateTime() {
		return utcDateTime;
	}

	public void setUtcDateTime(String utcDateTime) {
		this.utcDateTime = utcDateTime;
	}
}
