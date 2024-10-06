package com.hoteleria_app.hoteleria_app.controller.Reservation;

import com.hoteleria_app.hoteleria_app.dto.Reservation.RequestReservation;
import com.hoteleria_app.hoteleria_app.dto.Reservation.ResponseReservationDto;
import com.hoteleria_app.hoteleria_app.service.Email.EmailService;
import com.hoteleria_app.hoteleria_app.service.Reservation.ReservationService;
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
    private final ReservationService reservationService;
    private final EmailService emailService;

    public ReservationController(EmailService emailService, ReservationService reservationService) {
        this.reservationService = reservationService;
        this.emailService = emailService;
    }

    @PostMapping("/test")
    public ResponseEntity<ResponseReservationDto> createTestReservation(
            @RequestBody @Valid RequestReservation dataRoomReservation,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errors = new StringBuilder();
                bindingResult.getAllErrors().forEach((error) -> {
                    errors.append(error.getDefaultMessage() + ", ");
                });
                return ResponseEntity.status(200)
                        .body(new ResponseReservationDto("error", errors.toString()));
            }
            return ResponseEntity.status(200)
                    .body(new ResponseReservationDto("success", "Reservation created"));
        } catch (Exception error) {
            return ResponseEntity.status(200)
                    .body(new ResponseReservationDto("error", error.getMessage()));
        }
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
                        .body(new ResponseReservationDto("error", errors.toString()));
            }
            reservationService.createReservation(dataRoomReservation.getId_user(),
                    List.of(dataRoomReservation.getRoom_reservations()));
            return ResponseEntity.status(200)
                    .body(new ResponseReservationDto("success", "Reservation created"));
        } catch (Exception error) {
            return ResponseEntity.status(200)
                    .body(new ResponseReservationDto("error", error.getMessage()));
        }
    }
}
