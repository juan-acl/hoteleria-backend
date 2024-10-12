package com.hoteleria_app.hoteleria_app.controller.Room;

import com.hoteleria_app.hoteleria_app.dto.Room.*;
import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;
import com.hoteleria_app.hoteleria_app.service.Room.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(
            RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/createRoom")
    public ResponseEntity<ResponseRoomDto> createRoom(@RequestBody @Valid RequestCreateRoomDto room, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                });
                return ResponseEntity.status(400).body(new ResponseRoomDto(
                        "error", errorMessage.toString(), null));
            }
            RoomModel newRoom = roomService.createRoom(room);
            if (newRoom == null) {
                return ResponseEntity.status(200).body(new ResponseRoomDto(
                        "error", "Error", null));
            }
            return ResponseEntity.status(200).body(new ResponseRoomDto(
                    "success", "Room created", null));
        } catch (Exception e) {
            return ResponseEntity.status(200).body(new ResponseRoomDto("error"
                    , "Error", null));
        }
    }

    @PostMapping("/updateRoom")
    public ResponseEntity<ResponseRoomDto> updateRoom(@RequestBody @Valid RequestUpdateRoomDto updateRoom
            , BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                });
                return ResponseEntity.status(400).body(new ResponseRoomDto(
                        "error", errorMessage.toString(), null));
            }
            String room = roomService.updateRoom(updateRoom);
            if (room == null) {
                return ResponseEntity.status(200).body(new ResponseRoomDto(
                        "error", "Error:" + room, null));
            }
            return ResponseEntity.status(200).body(new ResponseRoomDto(
                    "success", "Room updated", null));
        } catch (Exception e) {
            return ResponseEntity.status(200).body(new ResponseRoomDto("error"
                    , "Error", null));
        }
    }

    @PostMapping("/deleteRoom")
    public ResponseEntity<ResponseRoomDto> deleteRoom(@RequestBody @Valid DeleteRoomDto deleteRoom,
                                                      BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                });
                return ResponseEntity.status(400).body(new ResponseRoomDto(
                        "error", errorMessage.toString(), null));
            }
            roomService.updateRoomActive(deleteRoom.getId_room(), false);
            return ResponseEntity.status(200).body(new ResponseRoomDto(
                    "success", "Room deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(200).body(new ResponseRoomDto("error"
                    , "Error", null));
        }
    }

    @GetMapping("/getRooms")
    public ResponseEntity<Set<RoomModel>> getRooms() {
        try {
            Set<RoomModel> rooms = roomService.getAllRooms();
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new HashSet<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getRoomById")
    public ResponseEntity<RoomModel> getRoomById(@RequestBody @Valid RequestGetRoomByldDto requestGetRoomByldDto, BindingResult bindingResult) {
        try {
            if(bindingResult.hasErrors()){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            RoomModel room = roomService.findById(requestGetRoomByldDto.getId_room());
            if(room == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(room, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
