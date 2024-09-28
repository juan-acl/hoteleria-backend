package com.hoteleria_app.hoteleria_app.controller.Access;

import com.hoteleria_app.hoteleria_app.dto.Access.RequestGetByIdUserDto;
import com.hoteleria_app.hoteleria_app.dto.Access.ResponseAccessDto;
import com.hoteleria_app.hoteleria_app.model.Access.AccessModel;
import com.hoteleria_app.hoteleria_app.service.Access.AccessService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api/access")
public class AccessController {
    private final AccessService accessService;

    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    @PostMapping("/getAllAccesByIdUser")
    public ResponseEntity<ResponseAccessDto> getAllAccesByIdUser(@RequestBody @Valid RequestGetByIdUserDto id_suer, BindingResult bindingResult) {
        try {
            if(bindingResult.hasErrors()) {
                StringBuilder message = new StringBuilder();
                bindingResult.getAllErrors().forEach((error) -> {
                    message.append(error.getDefaultMessage());
                });
                return ResponseEntity.status(400).body((new ResponseAccessDto("error", message.toString(), 0, null)));
            }
            Set<AccessModel> access = accessService.getAllAccesByIdUser(id_suer.getId_user());
            return ResponseEntity.status(200).body((new ResponseAccessDto("success", "Acces found", access.size(), access)));
        }catch(Exception eroor) {
            return ResponseEntity.status(500).body((new ResponseAccessDto("error", "Error on getAccesById", 0, null)));
        }
    }
}
