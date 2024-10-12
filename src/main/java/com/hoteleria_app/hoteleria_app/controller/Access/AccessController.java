package com.hoteleria_app.hoteleria_app.controller.Access;

import com.hoteleria_app.hoteleria_app.dto.Access.*;
import com.hoteleria_app.hoteleria_app.model.Access.AccessModel;
import com.hoteleria_app.hoteleria_app.service.Access.AccessService;
import com.hoteleria_app.hoteleria_app.service.Hotel.HotelService;
import com.hoteleria_app.hoteleria_app.service.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/api/access")
public class AccessController {
    private final AccessService accessService;
    private final HotelService hotelService;
    private final UserService userService;

    public AccessController(AccessService accessService,
                            HotelService hotelService,
                            UserService userService) {
        this.accessService = accessService;
        this.hotelService = hotelService;
        this.userService = userService;
    }

    @PostMapping("/getAllAccessByIdUser")
    public ResponseEntity<ResponseAccessHotelDto> getAllAccessByIdUser(@RequestBody @Valid RequestGetByIdUserDto id_suer, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder message = new StringBuilder();
                bindingResult.getAllErrors().forEach((error) -> {
                    message.append(error.getDefaultMessage());
                });
                return ResponseEntity.status(400).body((new ResponseAccessHotelDto("error", message.toString(), 0, null)));
            }
            Set<AccessModel> access =
                    accessService.getAllAccesByIdUser(id_suer.getId_user());
            return ResponseEntity.status(200).body((new ResponseAccessHotelDto("success", "Access found", access.size(), access)));
        } catch (Exception error) {
            return ResponseEntity.status(500).body((new ResponseAccessHotelDto("error", "Error on getAccessById: " + error.getMessage(), 0, null)));
        }
    }

    @PostMapping("/assignAccessUserByHotel")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseAccessDto> createAccessUserByHotel(@RequestBody @Valid RequestAssignmentBatchDto assignmentUser, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder error_message = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    error_message.append(error.getDefaultMessage());
                });
                return ResponseEntity.status(400).body(new ResponseAccessDto(
                        "error", error_message.toString()));
            }
            String success =
                    accessService.assignmentAccessUserByHotel(assignmentUser.getId_hotel(), assignmentUser.getId_user());
            if (success != "")
                return ResponseEntity.status(500).body(new ResponseAccessDto(
                        "error", "Error on assignment user: " + success));
            return ResponseEntity.status(500).body(new ResponseAccessDto(
                    "success", "Access assigned"));
        } catch (Exception error) {
            return ResponseEntity.status(500).body(new ResponseAccessDto(
                    "error",
                    "Error on assignment user: " + error.getMessage()));
        }
    }

    @PostMapping("/deleteAccessUserByRole")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseAccessDto> deleteAccessUserByRole(@RequestBody @Valid RequestDeleteAssignment assignmentUser, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder error_message = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    error_message.append(error.getDefaultMessage());
                });
                return ResponseEntity.status(400).body(new ResponseAccessDto(
                        "error", error_message.toString()));
            }
            AccessModel existAccess =
                    accessService.getAccessById(assignmentUser.getId_access());
            if (existAccess == null) {
                return ResponseEntity.status(400).body(new ResponseAccessDto(
                        "error", "Access not found"));
            }
            Boolean success =
                    accessService.deleteAccessByIdUser(assignmentUser.getId_access());
            if (!success)
                return ResponseEntity.status(500).body(new ResponseAccessDto(
                        "error", "Error on delete access"));
            return ResponseEntity.status(500).body(new ResponseAccessDto(
                    "success", "Access deleted"));
        } catch (Exception error) {
            return ResponseEntity.status(500).body(new ResponseAccessDto(
                    "error", "Error on delete access: " + error.getMessage()));
        }
    }
}
