package com.wildbody.kite.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
	public Docket postApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Kite").apiInfo(info()).select()
				.apis(RequestHandlerSelectors.basePackage("com.wildbody.kite")).paths(PathSelectors.ant("/api/**"))
				.build();
	}

	public ApiInfo info() {
		return new ApiInfoBuilder().title("Kite's Mobile Project").license("SSAFY-Kite license").version("1.0").build();
	}

	// ↓↓↓↓↓↓↓↓↓↓↓↓↓ swagger-ui의 cors에러를 해결
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
	}
//    ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}
