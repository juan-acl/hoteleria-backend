package com.hoteleria_app.hoteleria_app.service.Room;

import com.hoteleria_app.hoteleria_app.dto.Room.RequestCreateRoomDto;
import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;
import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;
import com.hoteleria_app.hoteleria_app.model.RoomType.RoomTypeModel;
import com.hoteleria_app.hoteleria_app.repository.Hotel.HotelRepository;
import com.hoteleria_app.hoteleria_app.repository.Room.RoomRepository;
import com.hoteleria_app.hoteleria_app.service.RoomType.RoomTypeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomTypeService roomTypeService;

    public RoomService(RoomRepository roomRepository,
                       RoomTypeService roomTypeService, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.roomTypeService = roomTypeService;
        this.hotelRepository = hotelRepository;
    }

    public RoomModel findById(Long id_hotel) {
        return roomRepository.findById(id_hotel).orElse(null);
    }

    public RoomModel createRoom(RequestCreateRoomDto room) {
        RoomTypeModel roomTypeModel =
                roomTypeService.getRoomTypeById(room.getId_room_type());
        if(roomTypeModel == null) {
            return null;
        }
        HotelModel hotelModel =
                hotelRepository.findById(room.getId_hotel()).orElse(null);
        if(hotelModel == null) {
            return null;
        }
        RoomModel newRoom = new RoomModel();
        newRoom.setRoomNumber(room.getRoomNumber());
        newRoom.setAvailable(1);
        newRoom.setActive(1);
        newRoom.setPrice(room.getPrice());
        newRoom.setIdRoomType(roomTypeModel);
        newRoom.setIdHotel(hotelModel);
        newRoom.setName(room.getName());
        return roomRepository.save(newRoom);
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

    public RoomModel findRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

}
