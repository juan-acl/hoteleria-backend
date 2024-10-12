package com.hoteleria_app.hoteleria_app.controller.Reservation;

import com.hoteleria_app.hoteleria_app.dto.Reservation.RequestReservation;
import com.hoteleria_app.hoteleria_app.dto.Reservation.ResponseReservationDto;
import com.hoteleria_app.hoteleria_app.service.Email.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final EmailService emailService;

    public ReservationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/createReservation")
    public ResponseEntity<ResponseReservationDto> createReservation(
            @RequestBody @Valid RequestReservation dataRoomReservation,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errors = new StringBuilder();
                bindingResult.getAllErrors().forEach((error) -> {
                    errors.append(error.getDefaultMessage() + ", ");
                });
                return ResponseEntity.status(200)
                        .body(new ResponseReservationDto("error",
                                errors.toString()));
            }
            emailService.registerReservation(dataRoomReservation.getId_user(),
                    List.of(dataRoomReservation.getRoom_reservations()));
            return ResponseEntity.status(200)
                    .body(new ResponseReservationDto("success", "Reservation " +
                            "created"));
        } catch (Exception error) {
            return ResponseEntity.status(200)
                    .body(new ResponseReservationDto("error",
                            error.getMessage()));
        }
    }

}
