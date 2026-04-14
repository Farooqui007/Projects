package com.example.SpringDataJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.SpringDataJPA.model.Student;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDataJpaApplication.class, args);
		
		StudentRepo repo = context.getBean(StudentRepo.class);
//		Student s1 = context.getBean(Student.class);
//		Student s2 = context.getBean(Student.class);
		Student s3 = context.getBean(Student.class);
//		
//		s1.setRollNo(1);
//		s1.setName("Anas");
//		s1.setMarks(95);
//		
//		s2.setRollNo(2);
//		s2.setName("Kamran");
//		s2.setMarks(100);
//		
		s3.setRollNo(3);
		s3.setName("Sundus");
		s3.setMarks(100);
		
		repo.save(s3);
//		repo.save(s3);
//		
//		System.out.println(repo.findByMarksGreaterThan(95));
		}

}
 