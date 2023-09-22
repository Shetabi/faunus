package com.faunus.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestFaunusApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(FaunusApiApplication::main).with(TestFaunusApiApplication.class).run(args);
	}

}
