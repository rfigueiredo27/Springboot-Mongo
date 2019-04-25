package com.accenture.videobot.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.accenture.videobot.model.RegistroEnvio;
import com.accenture.videobot.model.RegistroWelcomeFibra;
import com.accenture.videobot.util.Constants;
import com.accenture.videobot.util.JavaUtil;

public abstract class ExportData {
	
	public static String ExportWelcomeFibraCsv(MongoTemplate template) throws IOException{
		
		try{
			ArrayList<RegistroWelcomeFibra> regs = new ArrayList<RegistroWelcomeFibra>();
			
			Query query = new Query();
			query.with(new Sort(Sort.Direction.ASC, "DataInstalacao"));
			
			regs = (ArrayList<RegistroWelcomeFibra>) template.find(query, RegistroWelcomeFibra.class);
			
			String filePath = Constants.OUTPUT_FOLDER + "exportVideobot" + JavaUtil.GetTimeStampFile() + ".csv";
			
			File fout = new File(filePath);
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			bw.write("_id;PrimeiroNome;TelefoneContato");
			bw.newLine();
			
			for (RegistroWelcomeFibra reg: regs) {				
				String dataRow = reg.get_id()+";"+reg.getPrimeiroNome()+";"+reg.getTelefoneContato()+";"+reg.getTipoPromocao();
				bw.write(dataRow);
				bw.newLine();
			}
			
			bw.close();
			return "File Created";
		}catch (Exception e){
			return e.getMessage().toString();
		}
		
		
	}
	
	public static String ExportSentDataCsv(MongoTemplate template, ArrayList<RegistroEnvio> regs) throws IOException{
		
		try{
			
			String filePath = Constants.OUTPUT_FOLDER + "exportVideobotSentMessages" + JavaUtil.GetTimeStampFile() + ".csv";
			
			File fout = new File(filePath);
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			bw.write("DataHoraEnvio;TelefoneContato;CpfCliente;StatusEnvio");
			bw.newLine();
			
			for (RegistroEnvio reg: regs) {				
				String dataRow = reg.getDataHoraEnvio()+";"+reg.getTelefoneCliente()+";"+reg.getCpfCliente()+";"+reg.getStatusEnvio();
				bw.write(dataRow);
				bw.newLine();
			}
			
			bw.close();
			return "File Created";
		}catch (Exception e){
			return e.getMessage().toString();
		}
		
		
	}
	

}
