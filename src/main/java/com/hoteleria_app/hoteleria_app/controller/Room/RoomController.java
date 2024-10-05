package com.hoteleria_app.hoteleria_app.controller.Room;

import com.hoteleria_app.hoteleria_app.dto.Room.ResponseRoomDto;
import com.hoteleria_app.hoteleria_app.dto.Room.ResponseRoomReservedDto;
import com.hoteleria_app.hoteleria_app.repository.Room.RoomRepository;
import com.hoteleria_app.hoteleria_app.service.Room.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hoteleria_app.hoteleria_app.dto.Room.RequestIsReservedDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    private final RoomRepository roomRepository;
    private final RoomService roomService;

    public RoomController(RoomRepository roomRepository, RoomService roomService) {
        this.roomRepository = roomRepository;
        this.roomService = roomService;
    }

    @PostMapping("/isReserved")
    public ResponseEntity<ResponseRoomDto> isReserved(@RequestBody RequestIsReservedDto roomBody) {
        try {
            Long room = roomService.countReservedRoom(roomBody.getId(), roomBody.getInitialReservationDate(), roomBody.getFinalReservationDate());
            ResponseRoomReservedDto responseRoomReservedDto = new ResponseRoomReservedDto();
            responseRoomReservedDto.setCountReserved(room);
            if(room > 0) {
                responseRoomReservedDto.setIsReserved(true);
                return ResponseEntity.status(200).body(new ResponseRoomDto("success", "Room is reserved", responseRoomReservedDto));
            }
            responseRoomReservedDto.setIsReserved(false);
            return ResponseEntity.status(200).body(new ResponseRoomDto("success", "Room is not reserved",responseRoomReservedDto));
        } catch (Exception error) {
            return ResponseEntity.status(200).body(new ResponseRoomDto("success", "Error",null));
        }
    }

}
