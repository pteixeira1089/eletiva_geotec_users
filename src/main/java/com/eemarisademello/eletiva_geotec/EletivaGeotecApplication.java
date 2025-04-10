package com.eemarisademello.eletiva_geotec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.eemarisademello.eletiva_geotec.repository")
@EntityScan(basePackages = "com.eemarisademello.eletiva_geotec.model")
public class EletivaGeotecApplication {

	public static void main(String[] args) {
		SpringApplication.run(EletivaGeotecApplication.class, args);
	}

}
