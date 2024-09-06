package com.hoteleria_app.hoteleria_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoteleria_app.hoteleria_app.dto.EmailDto.EmailRequest;
import com.hoteleria_app.hoteleria_app.dto.EmailDto.EmailResponse;
import com.hoteleria_app.hoteleria_app.service.EmailService;

@RestController
@RequestMapping("/api")
public class SendEmail {
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            if (emailRequest.getBody() == null || emailRequest.getTo() == null || emailRequest.getSubject() == null) {
                return ResponseEntity.ok().body(new EmailResponse("All params are required!!", 400));
            }
            emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
            return ResponseEntity.ok().body(new EmailResponse("Email send succesfully", 200));
        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(new EmailResponse("Error on sending email" + e.getMessage() + " " + e.getCause(), 500));
        }
    }

}
