package com.manning.readinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	@SpringBootApplication这个注解包含了三个注解的功能：
	1。@Configuration:标示该类使用Spring基于Java的配置
	2。@ComponentScan:启用组建扫描，这样写的Web控制器类和其他组件才能被自动发现并注册为Spring应用上下文里的Bean
	3。@EnableAutoConfiguration:开启了Spring Boot自动配置的魔力，让你不用再写成篇的配置了
 */
@SpringBootApplication
public class ReadingListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingListApplication.class, args);
	}
}
