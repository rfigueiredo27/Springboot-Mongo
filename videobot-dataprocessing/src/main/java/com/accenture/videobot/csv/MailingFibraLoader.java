package com.accenture.videobot.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import com.accenture.videobot.model.RegistroExecutionId;
import com.accenture.videobot.model.RegistroMailingFibra;
import com.accenture.videobot.mongo.MongoWriter;
import com.accenture.videobot.util.Constants;
import com.accenture.videobot.util.JavaUtil;

public class MailingFibraLoader {

	private MongoTemplate mongoTemplate;
	private String csvFilePath;
	private Boolean hasHeaders;
	
	public MailingFibraLoader(MongoTemplate mongoTemplate, String csvFilePath, Boolean hasHeaders) {
		this.mongoTemplate = mongoTemplate;
		this.csvFilePath = csvFilePath;
		this.hasHeaders = hasHeaders;
	}
	
	public void LoadMailingFibraData(){
		
		//Movendo dados anteriores para a collection HsStgMailingFibra e apagando a STG
		MoverDadosStgHs();
		
		//Novo ExecutionId
		RegistroExecutionId execId = new RegistroExecutionId();
		
		String csvDelimitter = ",";
		String csvRow = "";
		BufferedReader reader = null;
		ArrayList<RegistroMailingFibra> regList = new ArrayList<RegistroMailingFibra>();
		
		System.out.println(JavaUtil.GetTimeStampBracket()+"START READING");
		
		try {
			reader = new BufferedReader(new FileReader(csvFilePath));
			int total = 1;
			
			
			while ((csvRow = reader.readLine()) != null) {
				String[] csvReg = csvRow.split("\\" + csvDelimitter,-1);
				
				if(total!=1 || !(hasHeaders)) { 
					regList.add(ParseMailingObject(csvReg,execId));
				}
				
				if (total%1000 == 0) {
					System.out.println(JavaUtil.GetTimeStampBracket()+"READING:"+total);
				}
				
				total++;
			}
			
			System.out.println(JavaUtil.GetTimeStampBracket()+"READING FINISHED");
			
			MongoWriter mw = new MongoWriter();
			mw.WriteMailingFibra(regList, mongoTemplate);
			
			mongoTemplate.save(execId);
			
		}catch (FileNotFoundException e){
			System.out.println(JavaUtil.GetTimeStampBracket()+"File Not Found:" + csvFilePath);
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private RegistroMailingFibra ParseMailingObject(String[] csvRow, RegistroExecutionId execId) {
		RegistroMailingFibra reg = new RegistroMailingFibra();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		
		//Data Instalação
		try {
			reg.setDataInstProduto(dateFormatter.parse(csvRow[0]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		reg.setExecutionId(execId.getExecutionId());
		reg.setPrimeiroNomeCliente(StringUtils.capitalize(csvRow[1].toLowerCase()));		
		reg.setNomeCliente(csvRow[2]);
		reg.setTelefoneCliente(csvRow[3]);
		reg.setTipoPromocao(csvRow[4]);
		
		//Data Inicial Serviço
		try {
			reg.setDataInicialServico(dateFormatter.parse(csvRow[5]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		reg.setMunicipioInstalacao(csvRow[6]);
		reg.setEstadoInstalacao(csvRow[7]);
		reg.setCpf(csvRow[8]);
		reg.setTipoTelefone(csvRow[3]);
		reg.setCodigoTipoProduto(csvRow[9]);
		reg.setPlanoProduto(csvRow[10]);
		return reg;
	}
	
	private void MoverDadosStgHs() {
		Query query = new Query();
		ArrayList<RegistroMailingFibra> regsStg = (ArrayList<RegistroMailingFibra>) mongoTemplate.find(query,RegistroMailingFibra.class);
		
		try {
			for (RegistroMailingFibra reg: regsStg) {
				mongoTemplate.save(reg,Constants.HS_STG_MAILING_FIBRA);
			}
			mongoTemplate.getCollection(Constants.STG_MAILING_FIBRA).deleteMany(new Document());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
