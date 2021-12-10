package com.example.course.util;

import java.security.NoSuchAlgorithmException;

public class testClass {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		String senha = "123456";
		
		senha = Util.md5(senha);
		
		System.out.println(senha);

	}

}
