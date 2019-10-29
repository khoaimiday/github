//package com.myclass.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//import org.thymeleaf.templateresolver.ITemplateResolver;
//
//import nz.net.ultraq.thymeleaf.LayoutDialect;
//
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.myclass")
//public class WebMvcConfig implements WebMvcConfigurer {
//
//	@Bean
//	public ITemplateResolver templateResolver() {
//		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//		resolver.setPrefix("classpath:/templates/");
//		resolver.setSuffix(".html");
//		resolver.setTemplateMode("HTML5");
//		resolver.setCacheable(false);
//		resolver.setCharacterEncoding("UTF-8");
//		return resolver;
//	}
//
//	@Bean
//	public SpringTemplateEngine templateEngine() {
//		SpringTemplateEngine engine = new SpringTemplateEngine();
//		engine.setTemplateResolver(templateResolver());
//		engine.addDialect(layoutDialect());
//		return engine;
//	}
//
//	@Bean
//	public ViewResolver viewResolver() {
//		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//		resolver.setTemplateEngine(templateEngine());
//		resolver.setCharacterEncoding("UTF-8");
//		return resolver;
//	}
//
//	@Bean
//	public LayoutDialect layoutDialect() {
//		return new LayoutDialect();
//	}
//
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		// PHẦN CẤU HÌNH CHO STATIC FILE
//		registry.addResourceHandler("*/**")
//		.addResourceLocations("classpath:/static/");
//		
//		registry.addResourceHandler("*/upload/**")
//		.addResourceLocations("classpath:/static/upload/");
//
////		// PHẦN CẤU HÌNH CHO SWAGGER
////		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
////
////		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//	}
//
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/api/**")
//		.allowedOrigins("*")
//		.allowedMethods("PUT", "GET", "POST", "DELETE")
//		.allowCredentials(false)
//		.maxAge(3600);
//	}
//}