package com.hoteleria_app.hoteleria_app.controller.RoomType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hoteleria_app.hoteleria_app.dto.RoomType.RequestCreateRoomTypeDto;
import com.hoteleria_app.hoteleria_app.dto.RoomType.RequestGetRoomTypeByIdDto;
import com.hoteleria_app.hoteleria_app.dto.RoomType.RequestUpdateRoomTypeDto;
import com.hoteleria_app.hoteleria_app.dto.RoomType.ResponseGetAllRoomTypesDto;
import com.hoteleria_app.hoteleria_app.dto.RoomType.ResponseGetByIdRoomTypeDto;
import com.hoteleria_app.hoteleria_app.dto.RoomType.ResponseRoomTypeDto;
import com.hoteleria_app.hoteleria_app.model.RoomType.RoomTypeModel;
import com.hoteleria_app.hoteleria_app.service.RoomType.RoomTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/roomType")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @PostMapping("/getAllRoomTypes")
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

    @PostMapping("/getRoomTypeById")
    public ResponseEntity<ResponseGetByIdRoomTypeDto> getRoomTypeById(
            @RequestBody @Valid RequestGetRoomTypeByIdDto id_room_type,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                });
                return ResponseEntity.status(400).body(
                        new ResponseGetByIdRoomTypeDto("error", errorMessage.toString(), null));
            }
            RoomTypeModel roomType =
                    roomTypeService.getRoomTypeById(id_room_type.getId_room_type());
            return ResponseEntity.status(200)
                    .body(new ResponseGetByIdRoomTypeDto("success", "Room type found", roomType));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseGetByIdRoomTypeDto("error",
                    "Internal server error: " + e.getMessage(), null));
        }
    }

    @PostMapping("/createRoomType")
    public ResponseEntity<ResponseRoomTypeDto> createRoomType(
            @RequestBody @Valid RequestCreateRoomTypeDto roomType, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                });
                return ResponseEntity.status(400)
                        .body(new ResponseRoomTypeDto("error", errorMessage.toString()));
            }
            RoomTypeModel newRoomType = roomTypeService.createRoomType(roomType);
            if (newRoomType == null) {
                return ResponseEntity.status(400)
                        .body(new ResponseRoomTypeDto("error", "Failed to create room type"));
            }

            return ResponseEntity.status(200)
                    .body(new ResponseRoomTypeDto("success", "Room type created"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ResponseRoomTypeDto("error", "Internal server error: " + e.getMessage()));
        }
    }

    @PostMapping("/updateRoomType")
    public ResponseEntity<ResponseRoomTypeDto> updateRoomType(
            @RequestBody @Valid RequestUpdateRoomTypeDto roomType, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                });
                return ResponseEntity.status(400)
                        .body(new ResponseRoomTypeDto("error", errorMessage.toString()));
            }
            RoomTypeModel existingRoomType =
                    roomTypeService.getRoomTypeById(roomType.getId_room_type());
            if (existingRoomType == null) {
                return ResponseEntity.status(404)
                        .body(new ResponseRoomTypeDto("error", "Room type not found"));
            }
            existingRoomType.setName(roomType.getName().trim());
            existingRoomType.setDescription(roomType.getDescription().trim());
            existingRoomType.setAmenities(roomType.getAmenities().trim());
            existingRoomType.setMaximumPeople(roomType.getMaximum_people());
            roomTypeService.updateRoomType(existingRoomType);
            return ResponseEntity.status(200)
                    .body(new ResponseRoomTypeDto("success", "Room type updated"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ResponseRoomTypeDto("error", "Internal server error: " + e.getMessage()));
        }
    }


}
