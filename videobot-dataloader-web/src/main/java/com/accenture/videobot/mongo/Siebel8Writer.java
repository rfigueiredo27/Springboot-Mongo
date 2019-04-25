package com.accenture.videobot.mongo;

import java.util.ArrayList;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.accenture.videobot.model.RegistroSiebel8;
import com.accenture.videobot.util.Constants;


public class Siebel8Writer {

	public Siebel8Writer() {}
	
	public void WriteRegisters(ArrayList<RegistroSiebel8> regs, MongoTemplate template) {
		for(int i=0;i<regs.size();i++) {
			//System.out.println(i + " - " + regs.get(i).getTelContato() + " - " + regs.get(i).getPedido() + " - " + regs.get(i).getCpf());
			template.insert(regs.get(i), Constants.STG_SIEBEL8_COLL_NAME);
		}
	}
	

}
