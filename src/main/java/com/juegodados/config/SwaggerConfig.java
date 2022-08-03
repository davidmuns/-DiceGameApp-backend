package com.juegodados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//localhost:8080/v2/api-docs
//localhost:8080/swagger-ui.html

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.juegodados.controllers"))
				//.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	}

	@SuppressWarnings("deprecation")
	private ApiInfo getApiInfo() {

		return new ApiInfo("API documentation", // NAME
				"Juego de dados", // DESCRIPTION
				"VERSION 1.0", // VERSION
				"TERMS OF SERVICE URL", // TERMS OF SERVICE URL
				"LICENSE", // LISENSE
				"https://www.youtube.com/c/DavidGMuns", // TERMS OF LICENSE URL
				"");
	}
}
