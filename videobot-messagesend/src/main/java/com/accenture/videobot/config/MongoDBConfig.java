package com.accenture.videobot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.accenture.videobot.model.VideobotMessagesend;
import com.accenture.videobot.repository.VideobotMessagesendRepository;

/*
*	Classe responsável por inserir no mongo inicialmente
*	Responsável: Renan Figueiredo
*	Caso não queira é só comentar tudo ;)
*/

@EnableMongoRepositories(basePackageClasses = VideobotMessagesendRepository.class)
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(VideobotMessagesendRepository repo) {
        return strings -> {
        	VideobotMessagesend vb = new VideobotMessagesend();
        	vb.setLink("http://www.oi.com.br");
        	vb.setPrimeiroNome("Renan");
        	vb.setTelefoneContato("21969437388");
        	repo.save(vb);
        };
    }

}
