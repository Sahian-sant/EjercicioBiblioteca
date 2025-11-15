package com.mx.ApirestBibliotecaMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication 
@EnableFeignClients
public class ApirestBibliotecaMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestBibliotecaMsApplication.class, args);
	}

}
