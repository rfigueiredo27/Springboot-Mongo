package com.accenture.videobot.model;

import java.util.Date;

public class RegistroEnvio {
	
	private Date dataHoraEnvio;
	private String cpfCliente;
	private String telefoneCliente;
	private String statusEnvio;
	
	public RegistroEnvio() {}
	
	public RegistroEnvio(Date dataHoraEnvio, String cpfCliente, String telefoneCliente, String statusEnvio) {
		this.dataHoraEnvio = dataHoraEnvio;
		this.cpfCliente = cpfCliente;
		this.telefoneCliente = telefoneCliente;
		this.statusEnvio = statusEnvio;
	}

	public Date getDataHoraEnvio() {
		return dataHoraEnvio;
	}

	public void setDataHoraEnvio(Date dataHoraEnvio) {
		this.dataHoraEnvio = dataHoraEnvio;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getStatusEnvio() {
		return statusEnvio;
	}

	public void setStatusEnvio(String statusEnvio) {
		this.statusEnvio = statusEnvio;
	}
}
