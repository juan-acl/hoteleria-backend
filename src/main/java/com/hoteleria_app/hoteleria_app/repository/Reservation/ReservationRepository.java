package com.hoteleria_app.hoteleria_app.repository.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationRepository, Long> {

}
