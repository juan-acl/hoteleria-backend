package com.hoteleria_app.hoteleria_app.service.Room;

import com.hoteleria_app.hoteleria_app.dto.Room.RequestCreateRoomDto;
import com.hoteleria_app.hoteleria_app.dto.Room.RequestUpdateRoomDto;
import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;
import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;
import com.hoteleria_app.hoteleria_app.model.RoomType.RoomTypeModel;
import com.hoteleria_app.hoteleria_app.repository.Hotel.HotelRepository;
import com.hoteleria_app.hoteleria_app.repository.Room.RoomRepository;
import com.hoteleria_app.hoteleria_app.service.RoomType.RoomTypeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

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

    public Set<RoomModel> getAllRooms() {
        return new HashSet<>(roomRepository.findAll());
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

    public RoomModel updateRoomActive(Long id, Boolean active) {
        return roomRepository.updateActive(id, active);
    }

    public String updateRoom(RequestUpdateRoomDto room) {
        RoomModel roomUpdate = roomRepository.findById(room.getId_room()).orElse(null);
        if(roomUpdate == null) {
            return "Room not found";
        }
        RoomTypeModel roomTypeModel =
                roomTypeService.getRoomTypeById(room.getId_room_type());
        if(roomTypeModel == null) {
            return "Room type not found";
        }
        HotelModel hotelModel =
                hotelRepository.findById(room.getId_hotel()).orElse(null);
        if(hotelModel == null) {
            return "Hotel not found";
        }
        roomUpdate.setRoomNumber(room.getRoomNumber());
        roomUpdate.setAvailable(room.getAvaliable());
        roomUpdate.setPrice(room.getPrice());
        roomUpdate.setIdRoomType(roomTypeModel);
        roomUpdate.setIdHotel(hotelModel);
        roomUpdate.setName(room.getName());
        roomRepository.save(roomUpdate);
        return null;
    }

    public Long countReservedRoom(Long id, LocalDateTime initialReservationDate, LocalDateTime finalReservationDate) {
        return roomRepository.countReservedRoom(id, initialReservationDate, finalReservationDate);
    }

    public RoomModel findRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

}
