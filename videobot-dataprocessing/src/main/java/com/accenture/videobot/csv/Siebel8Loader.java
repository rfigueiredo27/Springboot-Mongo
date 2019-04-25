package com.accenture.videobot.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.accenture.videobot.model.RegistroSiebel8;
import com.accenture.videobot.mongo.MongoWriter;
import com.accenture.videobot.util.JavaUtil;

public class Siebel8Loader {

	private MongoTemplate mongoTemplate;
	private String csvFilePath;
	private Boolean hasHeaders;
	
	public Siebel8Loader(MongoTemplate mongoTemplate, String csvFilePath, Boolean hasHeaders) {
		this.mongoTemplate = mongoTemplate;
		this.csvFilePath = csvFilePath;
		this.hasHeaders = hasHeaders;
	}
	
	public void LoadSiebel8Data(){
		
		String csvDelimitter = ";";
		String csvRow = "";
		BufferedReader reader = null;
		ArrayList<RegistroSiebel8> regList = new ArrayList<RegistroSiebel8>();
		
		System.out.println(JavaUtil.GetTimeStampBracket()+"START READING");
		
		try {
			reader = new BufferedReader(new FileReader(csvFilePath));
			int total = 1;
			
			
			while ((csvRow = reader.readLine()) != null) {
				String[] csvReg = csvRow.split("\\" + csvDelimitter,-1);
				if(total!=1 || !(hasHeaders)) { 
					regList.add(ParseSiebelObject(csvReg));
				}
				
				if (total%10000 == 0) {
					System.out.println(JavaUtil.GetTimeStampBracket()+"READING:"+total);
				}
				
				total++;
			}
			
			System.out.println(JavaUtil.GetTimeStampBracket()+"READING FINISHED");
			
			MongoWriter sw = new MongoWriter();
			sw.WriteRegistersSiebel8(regList, mongoTemplate);
			
		}catch (FileNotFoundException e){
			System.out.println(JavaUtil.GetTimeStampBracket()+"File Not Found:" + csvFilePath);
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private RegistroSiebel8 ParseSiebelObject(String[] csvReg) {
		RegistroSiebel8 reg = new RegistroSiebel8();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		reg.setNumeroOs(csvReg[0]);
		reg.setStatusOs(csvReg[1]);
		reg.setStatus(csvReg[2]);
		reg.setAction(csvReg[3]);
		//Date
		try {
			reg.setDataCriacaoOs(dateFormatter.parse(csvReg[4]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		reg.setProduto(csvReg[5]);		
		reg.setNomeCliente(csvReg[6]);
		reg.setTelefoneContato(csvReg[7]);
		reg.setCodigoProduto(csvReg[8]);
		reg.setTipoPromocao(csvReg[9]);
		
		return reg;
	}

}
