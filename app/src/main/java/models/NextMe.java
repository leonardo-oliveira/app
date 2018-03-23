package models;

/**
 * Created by William on 30/01/17.
 * Classe java para a
 */

public class NextMe {
	private Integer cod;
	private String vehicle_name;
	private String marca;
	private String modelo;
	private String data_hora_atual;
	private String latitude;
	private String longitude;
	private String address;
	private String driver_id;
	private String distancia;
	private String volume_tanques;
	private String capacidade_tanques;
	private String status_tanques;
	private String motorista;
	private String cpf_motorista;
	private String celular_empresa_motorista;
	private String celular_particular_motorista;
	private Integer codVeiculoW;
	private Integer codVeiculo;

	public NextMe(String address, String capacidade_tanques, String celular_empresa_motorista, String celular_particular_motorista, Integer cod, String cpf_motorista, String data_hora_atual, String distancia, String driver_id, String latitude, String longitude, String marca, String modelo, String motorista, String status_tanques, String vehicle_name, String volume_tanques, Integer cod_veiculo, Integer cod_veiculo_w) {
		this.address = address;
		this.capacidade_tanques = capacidade_tanques;
		this.celular_empresa_motorista = celular_empresa_motorista;
		this.celular_particular_motorista = celular_particular_motorista;
		this.cod = cod;
		this.cpf_motorista = cpf_motorista;
		this.data_hora_atual = data_hora_atual;
		this.distancia = distancia;
		this.driver_id = driver_id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.marca = marca;
		this.modelo = modelo;
		this.motorista = motorista;
		this.status_tanques = status_tanques;
		this.vehicle_name = vehicle_name;
		this.volume_tanques = volume_tanques;
		this.codVeiculo = cod_veiculo;
		this.codVeiculoW = cod_veiculo_w;
	}

	public String getVehicle_name() {
		return vehicle_name;
	}

	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getData_hora_atual() {
		return data_hora_atual;
	}

	public void setData_hora_atual(String data_hora_atual) {
		this.data_hora_atual = data_hora_atual;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getVolume_tanques() {
		return volume_tanques;
	}

	public void setVolume_tanques(String volume_tanques) {
		this.volume_tanques = volume_tanques;
	}

	public String getCapacidade_tanques() {
		return capacidade_tanques;
	}

	public void setCapacidade_tanques(String capacidade_tanques) {
		this.capacidade_tanques = capacidade_tanques;
	}

	public String getStatus_tanques() {
		return status_tanques;
	}

	public void setStatus_tanques(String status_tanques) {
		this.status_tanques = status_tanques;
	}

	public String getMotorista() {
		return motorista;
	}

	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}

	public String getCpf_motorista() {
		return cpf_motorista;
	}

	public void setCpf_motorista(String cpf_motorista) {
		this.cpf_motorista = cpf_motorista;
	}

	public String getCelular_empresa_motorista() {
		return celular_empresa_motorista;
	}

	public void setCelular_empresa_motorista(String celular_empresa_motorista) {
		this.celular_empresa_motorista = celular_empresa_motorista;
	}

	public String getCelular_particular_motorista() {
		return celular_particular_motorista;
	}

	public void setCelular_particular_motorista(String celular_particular_motorista) {
		this.celular_particular_motorista = celular_particular_motorista;
	}

	public Integer getCodVeiculo() {
		return codVeiculo;
	}

	public void setCodVeiculo(Integer codVeiculo) {
		this.codVeiculo = codVeiculo;
	}

	public Integer getCodVeiculoW() {
		return codVeiculoW;
	}

	public void setCodVeiculoW(Integer codVeiculoW) {
		this.codVeiculoW = codVeiculoW;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	@Override
	public String toString() {
		return "{\"NextMe\":{"
				+ "\"vehicle_name\":\"" + vehicle_name + "\""
				+ ", \"marca\":\"" + marca + "\""
				+ ", \"modelo\":\"" + modelo + "\""
				+ ", \"data_hora_atual\":\"" + data_hora_atual + "\""
				+ ", \"latitude\":\"" + latitude + "\""
				+ ", \"longitude\":\"" + longitude + "\""
				+ ", \"address\":\"" + address + "\""
				+ ", \"driver_id\":\"" + driver_id + "\""
				+ ", \"distancia\":\"" + distancia + "\""
				+ ", \"volume_tanques\":\"" + volume_tanques + "\""
				+ ", \"capacidade_tanques\":\"" + capacidade_tanques + "\""
				+ ", \"status_tanques\":\"" + status_tanques + "\""
				+ ", \"motorista\":\"" + motorista + "\""
				+ ", \"cpf_motorista\":\"" + cpf_motorista + "\""
				+ ", \"celular_empresa_motorista\":\"" + celular_empresa_motorista + "\""
				+ ", \"celular_particular_motorista\":\"" + celular_particular_motorista + "\""
				+ "}}";
	}
}
