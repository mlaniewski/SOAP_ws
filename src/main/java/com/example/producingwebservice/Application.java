package com.example.producingwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	//The url of the wsdl will look like: scheme://host:port/(servlet mapping uri)/(DefaultWsdl11Definition bean name).wsdl
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
