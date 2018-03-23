package models;

/**
 * Created by William on 18/05/17.
 * Classe java para a
 */

public class VeiculosUtilizado {
	private Integer vehicleId;
	private String vehicleName;
	private String data;

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "{\"VeiculosUtilizado\":{"
				+ "\"vehicleId\":\"" + vehicleId + "\""
				+ ", \"vehicleName\":\"" + vehicleName + "\""
				+ ", \"data\":\"" + data + "\""
				+ "}}";
	}
}
