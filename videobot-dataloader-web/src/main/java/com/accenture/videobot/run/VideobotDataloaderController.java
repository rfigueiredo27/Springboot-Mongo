package com.accenture.videobot.run;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.accenture.videobot.poi.Siebel8Reader;
import com.accenture.videobot.util.Constants;

@RestController
public class VideobotDataloaderController {

	@Autowired
	private MongoTemplate template;
	
	@RequestMapping("/loadfile/siebel8")
	private void LoadDataSiebel8() throws InvalidFormatException{
		Siebel8Reader sr = new Siebel8Reader();
		sr.LoadExcelFile(this.template, Constants.EXCEL_FILE);
	}
	
	@RequestMapping(path = "/clear/{collectionName}")
	private String ClearCollection(@PathVariable String collectionName) {
		String output;
		try {
			output = template.getCollection(collectionName).deleteMany(new Document()).toString();
		}catch (Exception e) {
			output = e.getMessage();
		}
		
		return output;
	}
	
	@RequestMapping("/teste")
	private String teste() {
		return "TESTE...";
	}
	
}
