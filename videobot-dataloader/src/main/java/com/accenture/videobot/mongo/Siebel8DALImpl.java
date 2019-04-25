package com.accenture.videobot.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.accenture.videobot.model.RegistroSiebel8;

@Repository
public class Siebel8DALImpl implements Siebel8DAL {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	public Siebel8DALImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public RegistroSiebel8 saveRegister(RegistroSiebel8 reg) {
		mongoTemplate.save(reg);
		return reg;
	}

}
