package com.ns.apps.vaccinestatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VaccineStatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaccineStatusApplication.class, args);
	}

}
