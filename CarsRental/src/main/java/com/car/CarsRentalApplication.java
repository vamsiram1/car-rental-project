package com.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class CarsRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsRentalApplication.class, args);
	}

}
