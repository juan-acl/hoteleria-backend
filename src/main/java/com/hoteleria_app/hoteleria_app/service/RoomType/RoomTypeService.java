package com.hoteleria_app.hoteleria_app.service.RoomType;

import org.springframework.stereotype.Service;
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
}
