package com.accenture.videobot.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AuditEnvioErro")
public class VideobotSendError {
		
	@Id
	private String _id;
	private String PrimeiroNome;
	private String TelefoneContato;
	private String Link;
	private Date DataHora = new Date();
	private String Produto = "Novos Clientes Oi Fibra";
	
	
	
	public String getProduto() {
		return Produto;
	}
	public void setProduto(String produto) {
		Produto = produto;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Date getDataHora() {
		return DataHora;
	}
	public void setDataHora(Date dataHora) {
		DataHora = dataHora;
	}
	public String getPrimeiroNome() {
		return PrimeiroNome;
	}
	public void setPrimeiroNome(String PrimeiroNome) {
		this.PrimeiroNome = PrimeiroNome;
	}
	public String getTelefoneContato() {
		return TelefoneContato;
	}
	public void setTelefoneContato(String TelefoneContato) {
		this.TelefoneContato = TelefoneContato;
	}
	public String getId() {
		return _id;
	}
	public void setId(String _id) {
		this._id = _id;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String Link) {
		this.Link = Link;
	}
}
