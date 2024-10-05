package com.hoteleria_app.hoteleria_app.service.Reservation;

import com.hoteleria_app.hoteleria_app.model.Reservation.ReservationModel;
import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;
import com.hoteleria_app.hoteleria_app.repository.Reservation.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationModel createReservation(ReservationModel reservation) {
        return reservationRepository.save(reservation);
    }

    public ReservationModel findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public ReservationModel updateReservation(ReservationModel reservation) {
        return reservationRepository.save(reservation);
    }
}
