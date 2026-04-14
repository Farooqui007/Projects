package com.Anas.Spring1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.Anas.Spring1.config.AppConfig;


public class App {
  public static void main(String[] args) {
    
	  ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	  
	  Alien obj =context.getBean(Alien.class);
	  System.out.println(obj.getAge());
	  obj.code();
	  Desktop dt = context.getBean(Desktop.class);
	  dt.complie();
	  
	  
	  
//	  ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//	  Alien obj = (Alien) context.getBean("alien");
//	  obj.setAge(21);
//	  System.out.println(obj.getAge());
//	  obj.code();
  }
}
