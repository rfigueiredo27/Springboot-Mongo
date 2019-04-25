package com.accenture.videobot.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.accenture.videobot.util.Constants;

@Document(collection=Constants.STG_MAILING_FIBRA)
public class RegistroMailingFibra {
	
	@Id
	private String _id;
	private String executionId;
	private Date DataInstProduto;
	private String PrimeiroNomeCliente;
	private String NomeCliente;
	private String TelefoneCliente;
	private String TipoPromocao;
	private Date DataInicialServico;
	private String MunicipioInstalacao;
	private String EstadoInstalacao;
	private String Cpf;
	private String TipoTelefone;
	private String CodigoTipoProduto;
	private String PlanoProduto;
	

	public RegistroMailingFibra() {}
	
	public RegistroMailingFibra(Date dataInstProduto, String primeiroNomeCliente, String nomeCliente, String telefoneCliente,
			String tipoPromocao, Date dataInicialServico, String municipioInstalacao, String estadoInstalacao) {
		this.DataInstProduto = dataInstProduto;
		this.PrimeiroNomeCliente = primeiroNomeCliente;
		this.NomeCliente = nomeCliente;
		this.TelefoneCliente = telefoneCliente;
		this.TipoPromocao = tipoPromocao;
		this.DataInicialServico = dataInicialServico;
		this.MunicipioInstalacao = municipioInstalacao;
		this.EstadoInstalacao = estadoInstalacao;
	}
	
	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	
	public Date getDataInstProduto() {
		return DataInstProduto;
	}
	public void setDataInstProduto(Date dataInstProduto) {
		DataInstProduto = dataInstProduto;
	}
	public String getPrimeiroNomeCliente() {
		return PrimeiroNomeCliente;
	}
	public void setPrimeiroNomeCliente(String primeiroNomeCliente) {
		PrimeiroNomeCliente = primeiroNomeCliente;
	}
	public String getNomeCliente() {
		return NomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}
	public String getTelefoneCliente() {
		return TelefoneCliente;
	}
	public void setTelefoneCliente(String telefoneCliente) {
		TelefoneCliente = telefoneCliente;
	}
	public String getTipoPromocao() {
		return TipoPromocao;
	}
	public void setTipoPromocao(String tipoPromocao) {
		TipoPromocao = tipoPromocao;
	}
	public Date getDataInicialServico() {
		return DataInicialServico;
	}
	public void setDataInicialServico(Date dataInicialServico) {
		DataInicialServico = dataInicialServico;
	}
	public String getMunicipioInstalacao() {
		return MunicipioInstalacao;
	}
	public void setMunicipioInstalacao(String municipioInstalacao) {
		MunicipioInstalacao = municipioInstalacao;
	}
	public String getEstadoInstalacao() {
		return EstadoInstalacao;
	}
	public void setEstadoInstalacao(String estadoInstalacao) {
		EstadoInstalacao = estadoInstalacao;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		if(cpf.length()>11) {
			this.Cpf = cpf.substring(3);
		}else {
			this.Cpf = cpf;
		}
	}
	
	public String getTipoTelefone() {
		return TipoTelefone;
	}
	
	//Assumindo que se o primeiro dígito for 9 trata-se de telefone Móvel
	//caso contrário telefone Fixo
	public void setTipoTelefone(String telefoneCliente) {
		if(telefoneCliente.substring(2,3).equals("9")) {
			TipoTelefone = "Movel";
		}else if (telefoneCliente.equalsIgnoreCase("null")){
			TipoTelefone = "Inexistente";
		}else {
			TipoTelefone = "Fixo";
		}
	}
	
	public String getCodigoTipoProduto() {
		return CodigoTipoProduto;
	}

	public void setCodigoTipoProduto(String codigoTipoProduto) {
		CodigoTipoProduto = codigoTipoProduto;
	}

	public String getPlanoProduto() {
		return PlanoProduto;
	}

	public void setPlanoProduto(String planoProduto) {
		PlanoProduto = planoProduto;
	}

}
