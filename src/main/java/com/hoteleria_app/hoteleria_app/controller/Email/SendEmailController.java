package com.hoteleria_app.hoteleria_app.controller.Email;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoteleria_app.hoteleria_app.dto.EmailDto.EmailRequest;
import com.hoteleria_app.hoteleria_app.dto.EmailDto.EmailResponse;
import com.hoteleria_app.hoteleria_app.service.Email.EmailService;

@RestController
@RequestMapping("/api")
public class SendEmailController {

    private final EmailService emailService;

    public SendEmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            if (emailRequest.getBody() == null || emailRequest.getTo() == null || emailRequest.getSubject() == null) {
                return ResponseEntity.ok().body(new EmailResponse(400, "All params are required!!"));
            }
//            emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
            return ResponseEntity.ok().body(new EmailResponse(200, "Email send succesfully"));
        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(new EmailResponse(500, "Error on sending email" + e.getMessage() + " " + e.getCause()));
        }
    }

}
