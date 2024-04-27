package com.firstdata.martin_dataanalytic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.firstdata.martin_dataanalytic.controller")
public class MartinDataAnalyticApplication {

	public static void main(String[] args) {
		SpringApplication.run(MartinDataAnalyticApplication.class, args);
	}

}
