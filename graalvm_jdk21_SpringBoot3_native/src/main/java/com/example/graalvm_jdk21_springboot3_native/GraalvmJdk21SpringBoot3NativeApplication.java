package com.example.graalvm_jdk21_springboot3_native;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GraalvmJdk21SpringBoot3NativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraalvmJdk21SpringBoot3NativeApplication.class, args);
	}

	@GetMapping("/hello")
	public ResponseEntity<String> hello(){

		return ResponseEntity.ok("hello_native_jdk21");
	}


}
