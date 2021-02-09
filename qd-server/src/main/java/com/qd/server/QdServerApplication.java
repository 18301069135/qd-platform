package com.qd.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = { "com.qd.*" })
@MapperScan("com.qd.**.mapper")
@EnableTransactionManagement
public class QdServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(QdServerApplication.class, args);
	}
}
