package com.accenture.videobot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.accenture.videobot.model.VideobotSendSucess;

@Repository
public interface VideobotSendSucessRepository extends CrudRepository<VideobotSendSucess, String> {
}
