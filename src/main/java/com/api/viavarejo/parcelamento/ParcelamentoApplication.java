package com.api.viavarejo.parcelamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.api.viavarejo.parcelamento"})
@ComponentScan(basePackages = {"com.api.viavarejo.parcelamento.controller.SelicClient"})
public class ParcelamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcelamentoApplication.class, args);
	}

}
