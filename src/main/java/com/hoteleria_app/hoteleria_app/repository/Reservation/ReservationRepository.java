package com.hoteleria_app.hoteleria_app.repository.Reservation;

import com.hoteleria_app.hoteleria_app.model.Reservation.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {

}
