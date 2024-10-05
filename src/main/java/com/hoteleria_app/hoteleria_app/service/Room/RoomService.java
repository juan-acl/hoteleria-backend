package com.hoteleria_app.hoteleria_app.service.Room;

import com.hoteleria_app.hoteleria_app.dto.Room.ResponseRoomReservedDto;
import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;
import com.hoteleria_app.hoteleria_app.repository.Room.RoomRepository;
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

    public Long countReservedRoom(Long id, LocalDateTime initialReservationDate, LocalDateTime finalReservationDate) {
        return roomRepository.countReservedRoom(id, initialReservationDate, finalReservationDate);
    }

}
