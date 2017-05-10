package com.zeven;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeven.bean.Author;

@ImportResource({ "classpath:spring-mvc.xml" })
@SpringBootApplication(scanBasePackages = "com.zeven")
@EnableAsync
public class ZemoApplication extends SpringBootServletInitializer
		implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ZemoApplication.class, args);
	}
	
	@Autowired
	private Author author;

	@RequestMapping("/")
	public String index() {
		return "Hello " + author.getName() + ",Your age is " + author.getAge();
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;  
		factory.setMaxFileSize("100MB"); //KB,MB  
		/// 设置总上传数据总大小  
		factory.setMaxRequestSize("100MB");
		//Sets the directory location where files will be stored.  
		//factory.setLocation("路径地址");  
		return factory.createMultipartConfig();
	}
}
