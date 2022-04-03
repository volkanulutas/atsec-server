package com.vem.atsecserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * @author volkanulutas
 * @since 01.01.2021
 */
@Controller
@SpringBootApplication
public class SecServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecServerApplication.class, args);
	}

}
