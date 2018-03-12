package com.winterarm.aspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.winterarm.aspect"})
public class AspectDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspectDemoApplication.class, args);
	}
}
