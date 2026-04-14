package com.Anas.Spring1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Alien {
	
	private int age;
	@Autowired
	private Computer com;
	
	public Alien(){
		System.out.println("object created");
	}
	
//	public Alien(int age , Computer com) {
//		this.age = age;
//		this.com = com;
//	}
	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public Computer getCom() {
		return com;
	}

	public void setCom(Computer com) {
		this.com = com;
	}

	public void code() {
		System.out.println("codingggggggg");
		com.complie();
		
	}
	
}
