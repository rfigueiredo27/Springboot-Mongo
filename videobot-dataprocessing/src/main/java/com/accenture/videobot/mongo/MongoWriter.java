package com.accenture.videobot.mongo;

import java.util.ArrayList;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.accenture.videobot.model.RegistroMailingFibra;
import com.accenture.videobot.model.RegistroSiebel8;
import com.accenture.videobot.util.Constants;
import com.accenture.videobot.util.JavaUtil;


public class MongoWriter {

	public MongoWriter() {}
	
	public void WriteRegistersSiebel8(ArrayList<RegistroSiebel8> regs, MongoTemplate template) {
		int total = 0;
		
		System.out.println(JavaUtil.GetTimeStampBracket()+"START WRITING");
		
		for(int i=0;i<regs.size();i++) {
			template.insert(regs.get(i), Constants.STG_SIEBEL8_COLL_NAME);
			total++;
			if (i%10000 == 0) {
				System.out.println(JavaUtil.GetTimeStampBracket()+"WRITING:"+i);
			}
		}
		System.out.println(JavaUtil.GetTimeStampBracket()+"WRITING FINISHED: "+total);
	}
	
	public void WriteMailingFibra(ArrayList<RegistroMailingFibra> regs, MongoTemplate template) {
		int total = 0;
		
		System.out.println(JavaUtil.GetTimeStampBracket()+"START WRITING");
		
		for(int i=0;i<regs.size();i++) {
			template.insert(regs.get(i), Constants.STG_MAILING_FIBRA);
			total++;
			if (i%10000 == 0) {
				System.out.println(JavaUtil.GetTimeStampBracket()+"WRITING:"+i);
			}
		}
		System.out.println(JavaUtil.GetTimeStampBracket()+"WRITING FINISHED: "+total);
	}

}
