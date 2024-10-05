package com.hoteleria_app.hoteleria_app.service.Reservation;

import com.hoteleria_app.hoteleria_app.model.Reservation.ReservationModel;
import com.hoteleria_app.hoteleria_app.model.ReservationDetail.ReservationDetailModel;
import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;
import com.hoteleria_app.hoteleria_app.repository.Reservation.ReservationRepository;
import com.hoteleria_app.hoteleria_app.repository.Room.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public ReservationModel findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public ReservationModel updateReservation(ReservationModel reservation) {
        return reservationRepository.save(reservation);
    }

    public Long isReserved(Long id, LocalDateTime initialReservationDate, LocalDateTime finalReservationDate) {
        return roomRepository.countReservedRoom(id, initialReservationDate, finalReservationDate);
    }

}
