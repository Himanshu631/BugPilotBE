package com.techio.bugpilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BugPilotApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugPilotApplication.class, args);
	}

}
