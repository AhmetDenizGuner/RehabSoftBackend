package com.hacettepe.rehabsoft;

import com.hacettepe.rehabsoft.service.DatabasePopulator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SpringBootApplication
@EnableJpaAuditing
public class RehabsoftApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(RehabsoftApplication.class, args);
		DatabasePopulator databasePopulator = (DatabasePopulator) context.getBean("databasePopulator");
	}


	@Bean
	public ModelMapper getModelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		//modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE); //HATA OLURSA BUNA GEC
		return modelMapper;
	}
/*
	@Bean
	public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[]{new ClassPathResource("users.json")});
		return factory;
	}
*/
}
