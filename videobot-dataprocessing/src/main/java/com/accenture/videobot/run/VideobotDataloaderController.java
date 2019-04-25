package com.accenture.videobot.run;

import java.io.IOException;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.accenture.videobot.csv.MailingFibraLoader;
import com.accenture.videobot.csv.Siebel8Loader;
import com.accenture.videobot.csv.ExportData;
import com.accenture.videobot.model.RegistroWelcomeFibra;
import com.accenture.videobot.processing.CreateSendingReport;
import com.accenture.videobot.processing.ProcessMailingFibraCpf;
import com.accenture.videobot.util.Constants;

@RestController
public class VideobotDataloaderController {

	@Autowired
	private MongoTemplate template;
	
	@RequestMapping("/videobot/loadfile/siebel8/{fileName}")
	private void LoadDataSiebel8Novo(@PathVariable String fileName){
		Siebel8Loader sl = new Siebel8Loader(template,Constants.FOLDER_SIEBEL8+fileName,true);
		sl.LoadSiebel8Data();
	}
	
	@RequestMapping("/videobot/loadfile/mailingfibra/{fileName}")
	private void LoadDataMailingFibra(@PathVariable String fileName){
		MailingFibraLoader mfl = new MailingFibraLoader(template,Constants.FOLDER_MAILING_FIBRA+fileName,false);
		mfl.LoadMailingFibraData();
	}
	
	@RequestMapping(path = "/videobot/clearcollection/{collectionName}")
	private String ClearCollection(@PathVariable String collectionName) {
		String output;
		try {
			output = template.getCollection(collectionName).deleteMany(new Document()).toString();
		}catch (Exception e) {
			output = e.getMessage();
		}
		
		return output;
	}
	
	@RequestMapping(path = "/videobot/process/welcomeoifibra")
	private String ProcessDataFibra() {
		try {
			ProcessMailingFibraCpf mf = new ProcessMailingFibraCpf(template);
			mf.ProcessDataMailing();
			return "FINALIZADO COM SUCESSO";
		} catch (Exception e) {
			return "ERRO: " + e.getMessage();
		}
	}
	
	@RequestMapping(path = "/videobot/createcsv/{csvFile}")
	private String ExportCsvFile(@PathVariable String csvFile) {
		if(csvFile.equals("welcomefibra")) {
			try {
				return ExportData.ExportWelcomeFibraCsv(template);
			} catch (IOException e) {
				return e.getMessage();
			}
		}else {
			return "No File Type Located";
		}
	}
	
	@RequestMapping(path = "/videobot/addsend/fibra",method = RequestMethod.POST)
	private String AddSend(@RequestBody RegistroWelcomeFibra wf) {
		try {
			return template.save(wf).toString();
		}catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(path = "/videobot/envio/sentReport")
	private void GerarRelatorioEnviados() throws IOException {
		CreateSendingReport csr = new CreateSendingReport(template);
		csr.GenerateSendingReport();
	}
	
	@RequestMapping("/teste")
	private String teste() {
		return "TESTE!!!";
	}
	
}
