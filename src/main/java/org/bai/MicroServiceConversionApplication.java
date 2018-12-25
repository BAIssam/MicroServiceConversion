package org.bai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("org.bai.proxy")
@EnableDiscoveryClient
public class MicroServiceConversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceConversionApplication.class, args);
	}

}

