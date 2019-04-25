package com.accenture.videobot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DimWelcomeOiFibra")
public class VideobotMessagesend {
		
	@Id
	private String _id;
	private String PrimeiroNome;
	private String TelefoneContato;
	private String Link;
	
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
