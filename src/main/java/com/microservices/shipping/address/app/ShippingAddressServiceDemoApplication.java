package com.microservices.shipping.address.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class ShippingAddressServiceDemoApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(ShippingAddressServiceDemoApplication.class);
	
	public static void main(String[] args) {
		LOGGER.info("Shipping Address Service Application : Started");
		SpringApplication.run(ShippingAddressServiceDemoApplication.class, args);
	}

	@Bean
	public Docket userServiceApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.microservices.shipping.address.app")).build();
	}
}
