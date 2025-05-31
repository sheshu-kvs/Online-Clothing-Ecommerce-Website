package com.sheshu;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordCheck {
	public static void main(String[] args) {
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    System.out.println("Encoded password for 'password123': " + encoder.encode("1234"));
	}
}
