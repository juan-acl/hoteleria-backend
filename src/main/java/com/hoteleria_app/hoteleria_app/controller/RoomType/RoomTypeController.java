package com.hoteleria_app.hoteleria_app.controller.RoomType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hoteleria_app.hoteleria_app.dto.RoomType.ResponseGetAllRoomTypesDto;
import com.hoteleria_app.hoteleria_app.model.RoomType.RoomTypeModel;
import com.hoteleria_app.hoteleria_app.service.RoomType.RoomTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


@RestController
@RequestMapping("/api/roomType")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @PostMapping("/getAllRoomTypes")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseGetAllRoomTypesDto> getAllRoomTypes() {
        try {
            List<RoomTypeModel> roomTypes = roomTypeService.getAllRoomTypes();
            return ResponseEntity.status(200).body(new ResponseGetAllRoomTypesDto("success",
                    "Room types found", roomTypes.size(), roomTypes));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseGetAllRoomTypesDto("error",
                    "Internal server error: " + e.getMessage(), 0, null));
        }
    }

}
