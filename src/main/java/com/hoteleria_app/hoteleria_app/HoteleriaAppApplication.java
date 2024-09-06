package com.hoteleria_app.hoteleria_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoteleria_app.hoteleria_app.dto.EmailDto.EmailResponse;

@SpringBootApplication
@RestController
public class HoteleriaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoteleriaAppApplication.class, args);
	}

	@GetMapping("/")
	public ResponseEntity<EmailResponse> index() {
		return ResponseEntity.ok().body(new EmailResponse("Welcome to hoteleria api", 200));
	}

}
