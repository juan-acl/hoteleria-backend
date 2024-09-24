package com.hoteleria_app.hoteleria_app.dto.Access;

import java.util.Set;
import com.hoteleria_app.hoteleria_app.model.Access.AccessModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAccessDto {
    private String status;
    private String message;
    private int count;
    private Set<AccessModel> access;
}
