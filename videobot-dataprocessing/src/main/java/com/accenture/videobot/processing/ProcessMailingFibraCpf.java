package com.accenture.videobot.processing;

import java.util.ArrayList;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.accenture.videobot.model.RegistroMailingFibra;
import com.accenture.videobot.model.RegistroMailingFibraAudit;
import com.accenture.videobot.model.RegistroWelcomeFibra;
import com.accenture.videobot.util.Constants;

public class ProcessMailingFibraCpf {
	
	private MongoTemplate template;
	private ArrayList<String> cpfs;
	
	public ProcessMailingFibraCpf(MongoTemplate template) {
		this.template = template;
		this.cpfs= (ArrayList<String>) template.findDistinct("Cpf", RegistroMailingFibra.class,String.class);
	}
	
	public void ProcessDataMailing() {
		//Apagando registros da collection
		template.getCollection(Constants.DIM_WELCOME_OI_FIBRA).deleteMany(new Document());
			
		for(String cpf:this.cpfs) {
			//Cliente Oi Total
			if(isOiTotal(cpf)) {
				if(hasMobileContactOiTotal(cpf)) {
					template.save(new RegistroWelcomeFibra(getRegistroMailing(cpf, "Oi Total", "Movel")));
				}else {
					//Cliente Oi Total sem contato Móvel
					RegistroMailingFibraAudit regAudit = new RegistroMailingFibraAudit(getRegistroMailing(cpf,"Oi Total"),Constants.MSG_MOTIVO_REJ_SEM_MOVEL);
					template.save(regAudit);
				}
			//Cliente Fibra Alone
			}else {
				if(hasMultipleProductsAlone(cpf)) {
					if(hasMobileContactAlone(cpf)) {
						template.save(new RegistroWelcomeFibra(getRegistroMailing(cpf, "Alone", "Movel")));
					}else {
						//Cliente Fibra Alone sem contáto móvel
						RegistroMailingFibraAudit regAudit = new RegistroMailingFibraAudit(getRegistroMailing(cpf,"Alone"),Constants.MSG_MOTIVO_REJ_SEM_MOVEL);
						template.save(regAudit);
					}
				}else {
					//Cliente Fibra Alone apenas com BL contratado
					RegistroMailingFibraAudit regAudit = new RegistroMailingFibraAudit(getRegistroMailing(cpf,"Alone"),Constants.MSG_MOTIVO_REJ_ALONE_BL);
					template.save(regAudit);
				}
			}
			
		}
	}
	
	private boolean isOiTotal(String cpf) {
		Query query = new Query();
		query.addCriteria(Criteria.where("Cpf").is(cpf));
		query.addCriteria(Criteria.where("TipoPromocao").is("Oi Total"));
		
		if(template.find(query, RegistroMailingFibra.class).isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	private boolean hasMobileContactOiTotal(String cpf) {
		Query query = new Query();
		query.addCriteria(Criteria.where("Cpf").is(cpf));
		query.addCriteria(Criteria.where("TipoPromocao").is("Oi Total"));
		query.addCriteria(Criteria.where("TipoTelefone").is("Movel"));
		
		if(template.find(query, RegistroMailingFibra.class).isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	private boolean hasMultipleProductsAlone(String cpf) {
		Query query = new Query();
		query.addCriteria(Criteria.where("Cpf").is(cpf));
		query.addCriteria(Criteria.where("CodigoTipoProduto").ne("Serviço de Internet"));
		
		if(template.find(query, RegistroMailingFibra.class).isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	private boolean hasMobileContactAlone(String cpf) {
		Query query = new Query();
		query.addCriteria(Criteria.where("Cpf").is(cpf));
		query.addCriteria(Criteria.where("CodigoTipoProduto").ne("Serviço de Internet"));
		query.addCriteria(Criteria.where("TipoTelefone").is("Movel"));
		
		if(template.find(query, RegistroMailingFibra.class).isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	private RegistroMailingFibra getRegistroMailing(String cpf,String tipoPromocao, String tipoTelefone){
		Query query = new Query();
		query.addCriteria(Criteria.where("Cpf").is(cpf));
		query.addCriteria(Criteria.where("TipoPromocao").is(tipoPromocao));
		query.addCriteria(Criteria.where("TipoTelefone").is(tipoTelefone));
		
		return template.findOne(query,RegistroMailingFibra.class); 
	}
	
	private RegistroMailingFibra getRegistroMailing(String cpf,String tipoPromocao){
		Query query = new Query();
		query.addCriteria(Criteria.where("Cpf").is(cpf));
		query.addCriteria(Criteria.where("TipoPromocao").is(tipoPromocao));
		
		return template.findOne(query,RegistroMailingFibra.class); 
	}


}
