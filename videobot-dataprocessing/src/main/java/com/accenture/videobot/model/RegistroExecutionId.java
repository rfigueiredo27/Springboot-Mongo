package com.accenture.videobot.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.accenture.videobot.util.Constants;

@Document(collection=Constants.CTL_EXECUTION_IDS)
public class RegistroExecutionId {
	@Id
	private String _id;
	private String executionId;
	private Date timeCreated;
	
	public RegistroExecutionId() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dataHora = new Date();
		
		this.executionId = dateFormat.format(dataHora);
		this.timeCreated = dataHora;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public Date getTimeCreated() {
		return this.timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

}
