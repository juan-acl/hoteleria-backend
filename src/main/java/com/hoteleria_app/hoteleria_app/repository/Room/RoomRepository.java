package com.hoteleria_app.hoteleria_app.repository.Room;

import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface RoomRepository extends JpaRepository<RoomModel, Long> {
    RoomModel findById(long id);

    @Query("UPDATE RoomModel r SET r.available = :available WHERE r.id = :id")
    RoomModel updateAvailable(@Param("id") long id, @Param("available") boolean available);

    @Query("UPDATE RoomModel r SET r.active = :active WHERE r.id = :id")
    RoomModel updateActive(@Param("id") long id, @Param("active") boolean active);

    @Query("SELECT COUNT(rd) FROM ReservationDetailModel rd " +
            "WHERE rd.idRoom.id = :id " +
            "AND (" +
            "(:initial_reservation_date BETWEEN rd.initialReservationDate AND rd.finalReservationDate) " +
            "OR (:final_reservation_date BETWEEN rd.initialReservationDate AND rd.finalReservationDate) " +
            "OR (rd.initialReservationDate BETWEEN :initial_reservation_date AND :final_reservation_date)" +
            ")")
    Long countReservedRoom(@Param("id") Long id, @Param("initial_reservation_date") LocalDateTime initial_reservation_date, @Param("final_reservation_date") LocalDateTime final_reservation_date);
}
