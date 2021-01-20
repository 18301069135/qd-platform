package com.qd.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class QdServerApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(QdServerApplication.class).run(args);
	}
}
