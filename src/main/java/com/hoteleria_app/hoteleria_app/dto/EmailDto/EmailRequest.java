package com.hoteleria_app.hoteleria_app.dto.EmailDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    private String to;
    private String subject;
    private String body;
}
