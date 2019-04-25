package com.accenture.videobot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accenture.videobot.model.LogUpdateLink;

@Repository
public interface LogUpdateLinkRepository extends CrudRepository<LogUpdateLink, String>{

}
