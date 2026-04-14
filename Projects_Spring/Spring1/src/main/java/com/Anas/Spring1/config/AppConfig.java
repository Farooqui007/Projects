package com.Anas.Spring1.config;

import com.Anas.Spring1.Alien;
import com.Anas.Spring1.Computer;
import  com.Anas.Spring1.Desktop;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

@Configurable
@ComponentScan("com.Anas.Spring1")
public class AppConfig {
//	
//	@Bean
//	public Alien alien(Computer com) {
//		
//		Alien obj = new Alien();
//		obj.setAge(21); 
//		obj.setCom(com);
//		return obj;
//	}
//	
////	@Bean(name = "desk")
//	@Bean
////	@Scope("prototype")
//	public Desktop desktop() {
//		return new Desktop();
//	}
//	
}
