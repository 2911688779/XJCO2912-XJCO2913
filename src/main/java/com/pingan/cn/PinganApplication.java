package com.pingan.cn;

import org.fusesource.jansi.Ansi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Map;

@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"com.pingan.cn.route" })
@Component
public class PinganApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PinganApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PinganApplication.class);
	}

}
