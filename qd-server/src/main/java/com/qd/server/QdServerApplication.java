package com.qd.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = { "com.qd.server.*" })
public class QdServerApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(QdServerApplication.class)
				.properties("spring.config.location=classpath:/application.yml").run(args);
	}
}
