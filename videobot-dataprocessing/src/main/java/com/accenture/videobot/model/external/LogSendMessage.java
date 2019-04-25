package com.accenture.videobot.model.external;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LogEnvio")
public class LogSendMessage {
	
	@Id
	private String _id;
	private String message;
	private String idSendMessage;
	private Date timestamp = new Date();
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIdSendMessage() {
		return idSendMessage;
	}
	public void setIdSendMessage(String idSendMessage) {
		this.idSendMessage = idSendMessage;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
