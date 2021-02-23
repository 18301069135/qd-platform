package com.qd.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = { "com.qd.*" })
@MapperScan("com.qd.**.mapper")
@EnableScheduling
public class QdServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(QdServerApplication.class, args);
	}
}
