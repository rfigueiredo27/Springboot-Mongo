package com.accenture.videobot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accenture.videobot.model.LogSendMessage;

@Repository
public interface LogSendMessageRepository extends CrudRepository<LogSendMessage, String>{

}
