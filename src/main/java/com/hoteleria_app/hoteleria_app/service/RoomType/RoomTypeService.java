package com.hoteleria_app.hoteleria_app.service.RoomType;

import org.springframework.stereotype.Service;
import com.hoteleria_app.hoteleria_app.dto.RoomType.RequestCreateRoomTypeDto;
import com.hoteleria_app.hoteleria_app.model.RoomType.RoomTypeModel;
import com.hoteleria_app.hoteleria_app.repository.RoomType.RoomTypeRepository;
import java.util.List;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;

    }

    public List<RoomTypeModel> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public RoomTypeModel getRoomTypeById(Long id_room_type) {
        return roomTypeRepository.findById(id_room_type).orElse(null);
    }

    public RoomTypeModel createRoomType(RequestCreateRoomTypeDto roomType) {
        RoomTypeModel newRoomType = new RoomTypeModel();
        newRoomType.setName(roomType.getName().trim());
        newRoomType.setDescription(roomType.getDescription().trim());
        newRoomType.setAmenities(roomType.getAmenities().trim());
        newRoomType.setMaximumPeople(roomType.getMaximumPeople());
        return roomTypeRepository.save(newRoomType);
    }
}
