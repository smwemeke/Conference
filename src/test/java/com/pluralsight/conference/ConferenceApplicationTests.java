package com.pluralsight.conference;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ConferenceApplicationTests {
@Autowired
	private PasswordEncoder encoder;
	@Test
	void contextLoads() {
		System.out.println("my encrytpted password: " + encoder.encode("pass"));
	}

}
