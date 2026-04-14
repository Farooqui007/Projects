package com.Anas.Spring1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Desktop implements Computer {
	
	public Desktop() {
		System.out.println("Desktop object created");
	}

	@Override
	public void complie() {
		System.out.println("compiling with Desktop");
		
	}
}
