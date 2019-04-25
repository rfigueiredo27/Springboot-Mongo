package com.accenture.videobot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accenture.videobot.model.VideobotMessagesend;

@Repository
public interface VideobotMessagesendRepository extends CrudRepository<VideobotMessagesend, String> {
}
