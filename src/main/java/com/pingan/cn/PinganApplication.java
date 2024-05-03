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
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.pingan.cn.*"})
@ComponentScan(basePackages = {"com.pingan.cn.lish.*","com.pingan.cn.guanqu","com.pingan.cn.nongzuowu" ,"com.pingan.cn.spatialdatamanage",
		"com.pingan.cn.chaland","com.pingan.cn.ningbomap.*","com.pingan.cn.aoyunhui","com.pingan.cn.lmsg", "com.pingan.cn.taxi", "com.pingan.cn.cityopen",
		"com.pingan.cn.fangxun", "com.pingan.cn.fangchan" })
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
