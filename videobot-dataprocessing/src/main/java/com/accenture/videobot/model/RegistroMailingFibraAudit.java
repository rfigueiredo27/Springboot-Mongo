package com.accenture.videobot.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.accenture.videobot.util.Constants;
import com.accenture.videobot.util.JavaUtil;

@Document(collection=Constants.AUDIT_PROC_WELCOME_FIBRA)
public class RegistroMailingFibraAudit {

	@Id
	private String _id;
	private String executionId;
	private String NomeCliente;
	private String Cpf;
	private String TelefoneCliente;
	private String MotivoRejeicao;
	private Date TimeCreated;
	
	public RegistroMailingFibraAudit() {}
	
	public RegistroMailingFibraAudit(String _id, String executionId, String nomeCliente, String cpf, String telefoneCliente, String motivoRejeicao) {
		this._id = _id;
		this.executionId = executionId;
		this.NomeCliente = nomeCliente;
		this.Cpf = cpf;
		this.TelefoneCliente = telefoneCliente;
		this.MotivoRejeicao = motivoRejeicao;
		this.TimeCreated = JavaUtil.GetTimeStampDate();
	}
	
	public RegistroMailingFibraAudit(RegistroMailingFibra reg, String motivoRejeicao) {
		this._id = reg.get_id();
		this.NomeCliente = reg.getNomeCliente();
		this.Cpf = reg.getCpf();
		this.TelefoneCliente = reg.getTelefoneCliente();
		this.MotivoRejeicao = motivoRejeicao;
		this.TimeCreated = JavaUtil.GetTimeStampDate();
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

	public String getNomeCliente() {
		return NomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getTelefoneCliente() {
		return TelefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		TelefoneCliente = telefoneCliente;
	}

	public String getMotivoRejeicao() {
		return MotivoRejeicao;
	}

	public void setMotivoRejeicao(String motivoRejeicao) {
		MotivoRejeicao = motivoRejeicao;
	}

	public Date getTimeCreated() {
		return TimeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		TimeCreated = timeCreated;
	}


}
