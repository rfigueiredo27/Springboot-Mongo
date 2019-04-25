package com.accenture.videobot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.accenture.videobot.model.VideobotSendError;

@Repository
public interface VideobotSendErrorRepository extends CrudRepository<VideobotSendError, String> {
}
