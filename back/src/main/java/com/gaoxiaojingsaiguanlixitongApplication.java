package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan(value = "com.ServletContextListener")
@MapperScan(basePackages = {"com.dao"})
public class gaoxiaojingsaiguanlixitongApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(gaoxiaojingsaiguanlixitongApplication.class, args);
	}


	// 该方法用于支持在传统的Servlet容器中运行Spring Boot应用，提供war包的支持
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		// 返回配置Spring Boot应用的Builder，指定主类gaoxiaojingsaiguanlixitongApplication作为应用的入口
		return applicationBuilder.sources(gaoxiaojingsaiguanlixitongApplication.class);
    }
}
