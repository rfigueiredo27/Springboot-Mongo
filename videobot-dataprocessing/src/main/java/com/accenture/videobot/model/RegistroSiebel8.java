package com.accenture.videobot.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.accenture.videobot.util.Constants;

@Document(collection=Constants.STG_SIEBEL8_COLL_NAME)
public class RegistroSiebel8 {
	
		@Id
		private String _id;
		private String NumeroOs;
		private String StatusOs;
		private String Status;
		private String Action;
		private Date DataCriacaoOs;
		private String Produto;
		private String NomeCliente;
		private String TelefoneContato;
		private String CodigoProduto;
		private String TipoPromocao;
		private String TipoCliente;
		private String CpfCnpj;

	public RegistroSiebel8() {
		
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getNumeroOs() {
		return NumeroOs;
	}

	public void setNumeroOs(String numeroOs) {
		NumeroOs = numeroOs;
	}

	public String getStatusOs() {
		return StatusOs;
	}

	public void setStatusOs(String statusOs) {
		StatusOs = statusOs;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public Date getDataCriacaoOs() {
		return DataCriacaoOs;
	}

	public void setDataCriacaoOs(Date dataCriacaoOs) {
		DataCriacaoOs = dataCriacaoOs;
	}

	public String getProduto() {
		return Produto;
	}

	public void setProduto(String produto) {
		Produto = produto;
	}

	public String getNomeCliente() {
		return NomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}

	public String getTelefoneContato() {
		return TelefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		TelefoneContato = telefoneContato;
	}

	public String getCodigoProduto() {
		return CodigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		CodigoProduto = codigoProduto;
	}

	public String getTipoPromocao() {
		return TipoPromocao;
	}

	public void setTipoPromocao(String tipoPromocao) {
		TipoPromocao = tipoPromocao;
	}

	public String getTipoCliente() {
		return TipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		TipoCliente = tipoCliente;
	}

	public String getCpfCnpj() {
		return CpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		CpfCnpj = cpfCnpj;
	}

}
