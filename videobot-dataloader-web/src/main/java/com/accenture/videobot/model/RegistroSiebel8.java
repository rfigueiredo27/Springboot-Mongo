package com.accenture.videobot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="stg_siebel8")
public class RegistroSiebel8 {
	@Id
	private String _id;
	private String TelContato;
	private String Pedido;
	private String DataPedido;
	private String StatusPedido;
	private String DataStatus;
	private String Cpf;
	private String Nome;
	private String NumConta;
	private String Endereco;
	private String Gpon;
	private String IdBundle;
	private String IdAcessoVoip;
	private String NumeroVoip;
	private String ActionCd;
	private String BandaLarga;
	private String AcaoBl;
	private String PlanoTv;
	private String AcaoTv;
	private String NumeroPortado;
	private String Oferta;
	private String AcaoOferta;
	private String ClasseBundle;
	private String Atividade;
	private String UsuarioAbertura;
	private String TipoCanal;
	private String CodPdv;
	private String PontoVenda;
	private String Vendedor63;
	private String Campanha;
	private String CodMotivoCancPedido;
	private String MotivoCancPedido;
	private String MotivoPendencia;
	
	public RegistroSiebel8() {
		
	}

	public String getTelContato() {
		return TelContato;
	}

	public void setTelContato(String telContato) {
		TelContato = telContato;
	}

	public String getPedido() {
		return Pedido;
	}

	public void setPedido(String pedido) {
		Pedido = pedido;
	}

	public String getDataPedido() {
		return DataPedido;
	}

	public void setDataPedido(String dataPedido) {
		DataPedido = dataPedido;
	}

	public String getStatusPedido() {
		return StatusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		StatusPedido = statusPedido;
	}

	public String getDataStatus() {
		return DataStatus;
	}

	public void setDataStatus(String dataStatus) {
		DataStatus = dataStatus;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getNumConta() {
		return NumConta;
	}

	public void setNumConta(String numConta) {
		NumConta = numConta;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	public String getGpon() {
		return Gpon;
	}

	public void setGpon(String gpon) {
		Gpon = gpon;
	}

	public String getIdBundle() {
		return IdBundle;
	}

	public void setIdBundle(String idBundle) {
		IdBundle = idBundle;
	}

	public String getIdAcessoVoip() {
		return IdAcessoVoip;
	}

	public void setIdAcessoVoip(String idAcessoVoip) {
		IdAcessoVoip = idAcessoVoip;
	}

	public String getNumeroVoip() {
		return NumeroVoip;
	}

	public void setNumeroVoip(String numeroVoip) {
		NumeroVoip = numeroVoip;
	}

	public String getActionCd() {
		return ActionCd;
	}

	public void setActionCd(String actionCd) {
		ActionCd = actionCd;
	}

	public String getBandaLarga() {
		return BandaLarga;
	}

	public void setBandaLarga(String bandaLarga) {
		BandaLarga = bandaLarga;
	}

	public String getAcaoBl() {
		return AcaoBl;
	}

	public void setAcaoBl(String acaoBl) {
		AcaoBl = acaoBl;
	}

	public String getPlanoTv() {
		return PlanoTv;
	}

	public void setPlanoTv(String planoTv) {
		PlanoTv = planoTv;
	}

	public String getAcaoTv() {
		return AcaoTv;
	}

	public void setAcaoTv(String acaoTv) {
		AcaoTv = acaoTv;
	}

	public String getNumeroPortado() {
		return NumeroPortado;
	}

	public void setNumeroPortado(String numeroPortado) {
		NumeroPortado = numeroPortado;
	}

	public String getOferta() {
		return Oferta;
	}

	public void setOferta(String oferta) {
		Oferta = oferta;
	}

	public String getAcaoOferta() {
		return AcaoOferta;
	}

	public void setAcaoOferta(String acaoOferta) {
		AcaoOferta = acaoOferta;
	}

	public String getClasseBundle() {
		return ClasseBundle;
	}

	public void setClasseBundle(String classeBundle) {
		ClasseBundle = classeBundle;
	}

	public String getAtividade() {
		return Atividade;
	}

	public void setAtividade(String atividade) {
		Atividade = atividade;
	}

	public String getUsuarioAbertura() {
		return UsuarioAbertura;
	}

	public void setUsuarioAbertura(String usuarioAbertura) {
		UsuarioAbertura = usuarioAbertura;
	}

	public String getTipoCanal() {
		return TipoCanal;
	}

	public void setTipoCanal(String tipoCanal) {
		TipoCanal = tipoCanal;
	}

	public String getPontoVenda() {
		return PontoVenda;
	}

	public void setPontoVenda(String pontoVenda) {
		PontoVenda = pontoVenda;
	}

	public String getVendedor63() {
		return Vendedor63;
	}

	public void setVendedor63(String vendedor63) {
		Vendedor63 = vendedor63;
	}

	public String getCampanha() {
		return Campanha;
	}

	public void setCampanha(String campanha) {
		Campanha = campanha;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getCodPdv() {
		return CodPdv;
	}

	public void setCodPdv(String codPdv) {
		CodPdv = codPdv;
	}

	public String getCodMotivoCancPedido() {
		return CodMotivoCancPedido;
	}

	public void setCodMotivoCancPedido(String codMotivoCancPedido) {
		CodMotivoCancPedido = codMotivoCancPedido;
	}

	public String getMotivoCancPedido() {
		return MotivoCancPedido;
	}

	public void setMotivoCancPedido(String motivoCancPedido) {
		MotivoCancPedido = motivoCancPedido;
	}

	public String getMotivoPendencia() {
		return MotivoPendencia;
	}

	public void setMotivoPendencia(String motivoPendencia) {
		MotivoPendencia = motivoPendencia;
	}

}
