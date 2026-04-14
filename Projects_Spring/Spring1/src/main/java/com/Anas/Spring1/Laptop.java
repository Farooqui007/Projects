package com.Anas.Spring1;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {
	


//	public Laptop() {
//		System.out.println("created Laptop");
//	}
	
	
	public void complie() {
		System.out.println("compiling with laptop ");
	}
}
