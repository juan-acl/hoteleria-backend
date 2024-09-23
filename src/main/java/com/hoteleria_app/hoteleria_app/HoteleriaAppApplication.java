package com.hoteleria_app.hoteleria_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hoteleria_app.hoteleria_app.dto.EmailDto.EmailResponse;

@SpringBootApplication
@RestController
public class HoteleriaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoteleriaAppApplication.class, args);
	}

	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<EmailResponse> index() {
		return ResponseEntity.ok().body(new EmailResponse(200, "Welcome to hoteleria api"));
	}

}
