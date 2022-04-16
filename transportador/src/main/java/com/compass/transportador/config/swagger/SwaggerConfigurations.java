package com.compass.transportador.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {
	
	@Bean
	public Docket stateApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.compass.transportador"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(apiInfo());	
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
		.title("Projeto Final Compass.UOL - Microserviço de Transporte")
		.description("Documentação da API - Microserviço de Transporte")
		.version("1.0")
		.build();
	}
}
