package com.hoteleria_app.hoteleria_app.service.Room;

import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;
import com.hoteleria_app.hoteleria_app.repository.Room.RoomRepository;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomModel findById(Long id_hotel) {
        return roomRepository.findById(id_hotel).orElse(null);
    }

    public RoomModel createRoom(RoomModel room) {
        return roomRepository.save(room);
    }

    public RoomModel updateRoomAvailable(Long id, Boolean available) {
        return roomRepository.updateAvailable(id, available);
    }

    public RoomModel updateRoomActive(Long id, Boolean active) {
        return roomRepository.updateActive(id, active);
    }

    public RoomModel updateRoom(RoomModel room) {
        return roomRepository.save(room);
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Long countReservedRoom(Long id, LocalDateTime initialReservationDate, LocalDateTime finalReservationDate) {
        return roomRepository.countReservedRoom(id, initialReservationDate, finalReservationDate);
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public RoomModel findRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

}
