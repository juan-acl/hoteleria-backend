package com.hoteleria_app.hoteleria_app.repository.Room;

import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<RoomModel, Long> {
    RoomModel findById(long id);

    @Query("UPDATE RoomModel r SET r.available = :available WHERE r.id = :id")
    RoomModel updateAvailable(@Param("id") long id, @Param("available") boolean available);

    @Query("UPDATE RoomModel r SET r.active = :active WHERE r.id = :id")
    RoomModel updateActive(@Param("id") long id, @Param("active") boolean active);
}
