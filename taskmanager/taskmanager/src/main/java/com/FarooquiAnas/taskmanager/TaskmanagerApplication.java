package com.FarooquiAnas.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication = 3 annotations in one:
//   @Configuration       → this class can define Spring Beans
//   @EnableAutoConfiguration → auto-configures MongoDB, Jackson, Tomcat etc.
//   @ComponentScan       → scans all sub-packages for @Service, @Controller etc.
@SpringBootApplication
public class TaskmanagerApplication {

    public static void main(String[] args) {
        // Boots up the embedded Tomcat server and wires everything together
        SpringApplication.run(TaskmanagerApplication.class, args);
    }
}