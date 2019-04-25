package com.accenture.videobot.processing;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.accenture.videobot.csv.ExportData;
import com.accenture.videobot.model.RegistroEnvio;
import com.accenture.videobot.model.RegistroMailingFibra;
import com.accenture.videobot.model.external.VideobotSendError;
import com.accenture.videobot.model.external.VideobotSendSucess;

public class CreateSendingReport {
	
	private MongoTemplate template;
	private ArrayList<RegistroEnvio> sentRows = new ArrayList<RegistroEnvio>();
	
	public CreateSendingReport(MongoTemplate template) {
		this.template = template;
	}
	
	public void GenerateSendingReport() throws IOException {
		ArrayList<VideobotSendSucess> regsSuccess = (ArrayList<VideobotSendSucess>) template.findAll(VideobotSendSucess.class);
		ArrayList<VideobotSendError> regsError = (ArrayList<VideobotSendError>) template.findAll(VideobotSendError.class);
		
		for (VideobotSendSucess vss: regsSuccess) {
			RegistroMailingFibra regStg = new RegistroMailingFibra();
			Query query = new Query();
			query.addCriteria(Criteria.where("TelefoneCliente").is(vss.getTelefoneContato()));
			
			try {
				regStg = template.findOne(query,RegistroMailingFibra.class);
				this.sentRows.add(new RegistroEnvio(vss.getDataHora(),regStg.getCpf(),vss.getTelefoneContato(),"Sucesso"));
			}catch(Exception e) {
				this.sentRows.add(new RegistroEnvio(vss.getDataHora(),"",vss.getTelefoneContato(),"Sucesso"));
			}
		}
		
		for (VideobotSendError vse: regsError) {
			RegistroMailingFibra regStg = new RegistroMailingFibra();
			Query query = new Query();
			query.addCriteria(Criteria.where("TelefoneCliente").is(vse.getTelefoneContato()));
			
			try {
				regStg = template.findOne(query,RegistroMailingFibra.class);
				this.sentRows.add(new RegistroEnvio(vse.getDataHora(),regStg.getCpf(),vse.getTelefoneContato(),"Erro"));
			}catch(Exception e) {
				this.sentRows.add(new RegistroEnvio(vse.getDataHora(),"",vse.getTelefoneContato(),"Erro"));
			}
		}
		
		ExportData.ExportSentDataCsv(this.template,this.sentRows);
		
	}

}
