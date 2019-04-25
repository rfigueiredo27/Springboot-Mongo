package com.accenture.videobot.model;


import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.accenture.videobot.util.Constants;

@Document(collection=Constants.DIM_WELCOME_OI_FIBRA)
public class RegistroWelcomeFibra {
	@Id
	private String _id;
	private String executionId;
	private String PrimeiroNome;
	private String TelefoneContato;
	private String Link;
	private Date DataInstalacao;
	private String Cpf;
	private String TipoPromocao;

	public RegistroWelcomeFibra() {}
	
	public RegistroWelcomeFibra(String _id, String executionId, String primeiroNome, String telefoneContato, Date dataInstalacao,String cpf,String tipoPromocao) {
		this._id = _id;
		this.executionId = executionId;
		this.PrimeiroNome = primeiroNome;
		this.TelefoneContato = telefoneContato;
		this.DataInstalacao = dataInstalacao;
		this.Cpf = cpf;
		this.Link = "";
		this.TipoPromocao = tipoPromocao;
	}
	
//	public RegistroWelcomeFibra(String primeiroNome, String telefoneContato) {
//		this.PrimeiroNome = primeiroNome;
//		this.TelefoneContato = telefoneContato;
//		this.Link = "";
//	}
	
	public RegistroWelcomeFibra(RegistroMailingFibra reg) {
		this._id = reg.get_id();
		this.executionId = reg.getExecutionId();
		this.PrimeiroNome = reg.getPrimeiroNomeCliente();
		this.TelefoneContato = reg.getTelefoneCliente();
		this.DataInstalacao = reg.getDataInstProduto();
		this.Cpf = reg.getCpf();
		this.Link = "";
		this.TipoPromocao = reg.getTipoPromocao();
	}
	
//	public RegistroWelcomeFibra(String _id, String primeiroNome, String telefoneContato, String link) {
//		this._id = _id;
//		this.PrimeiroNome = primeiroNome;
//		this.TelefoneContato = telefoneContato;
//		this.Link = link;
//	}
	
	
	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getPrimeiroNome() {
		return PrimeiroNome;
	}
	public void setPrimeiroNome(String primeiroNome) {
		this.PrimeiroNome = primeiroNome;
	}
	public String getTelefoneContato() {
		return TelefoneContato;
	}
	public void setTelefoneContato(String telefoneContato) {
		this.TelefoneContato = telefoneContato;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		this.Link = link;
	}

	public Date getDataInstalacao() {
		return DataInstalacao;
	}

	public void setDataInstalacao(Date dataInstalacao) {
		DataInstalacao = dataInstalacao;
	}
	
	public String getTipoPromocao() {
		return TipoPromocao;
	}

	public void setTipoPromocao(String tipoPromocao) {
		TipoPromocao = tipoPromocao;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}
}
