package com.hoteleria_app.hoteleria_app.controller.Hotel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.hoteleria_app.hoteleria_app.dto.HotelDto.RequestCreateHotelDto;
import com.hoteleria_app.hoteleria_app.dto.HotelDto.RequestGetHotelByIdDto;
import com.hoteleria_app.hoteleria_app.dto.HotelDto.RequestUpdateHotelDto;
import com.hoteleria_app.hoteleria_app.dto.HotelDto.ResponseHotelDto;
import com.hoteleria_app.hoteleria_app.dto.HotelDto.ResponseGetAllHotelDto;
import com.hoteleria_app.hoteleria_app.dto.HotelDto.ResponseGetHotelByIdDto;
import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;
import com.hoteleria_app.hoteleria_app.service.Hotel.HotelService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/getAllHotels")
    public ResponseGetAllHotelDto getAllHotels() {
        try {
            List<HotelModel> hotels = hotelService.getAllHotels();
            return new ResponseGetAllHotelDto(200, "Success get all hotels",
                    hotels.size(), hotels);
        } catch (Exception error) {
            return new ResponseGetAllHotelDto(500,
                    "Internal server error: " + error.getMessage(),
                    0, null);
        }
    }

    @PostMapping("/getHotelById")
    public ResponseEntity<ResponseGetHotelByIdDto> getHotelById(
            @RequestBody @Valid RequestGetHotelByIdDto id_hotel,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                });
                return ResponseEntity.status(400)
                        .body(new ResponseGetHotelByIdDto(400,
                                errorMessage.toString(), null));
            }
            HotelModel hotel =
                    hotelService.getHotelById(id_hotel.getId_hotel());
            return ResponseEntity.status(200)
                    .body(new ResponseGetHotelByIdDto(200, "Success get hotel" +
                            " by id", hotel));
        } catch (Exception error) {
            return ResponseEntity.status(500).body(new ResponseGetHotelByIdDto(500,
                    "Internal server error: " + error.getMessage(), null));
        }
    }

    @PostMapping("/createHotel")
    public ResponseEntity<ResponseHotelDto> createHotel(
            @RequestBody @Valid RequestCreateHotelDto hotel,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append(",");
                });
                return ResponseEntity.status(400)
                        .body(new ResponseHotelDto(400,
                                errorMessage.toString()));
            }
            hotelService.createHotel(hotel);
            return ResponseEntity.status(200)
                    .body(new ResponseHotelDto(200, "Success create hotel"));
        } catch (Exception error) {
            return ResponseEntity.status(500).body(
                    new ResponseHotelDto(500,
                            "Internal server error: " + error.getMessage()));
        }
    }

    @PostMapping("/updateHotel")
    public ResponseEntity<ResponseHotelDto> updateHotel(
            @RequestBody @Valid RequestUpdateHotelDto hotel,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append(", ");
                });
                return ResponseEntity.status(400)
                        .body(new ResponseHotelDto(400,
                                errorMessage.toString()));
            }
            HotelModel existingHotel =
                    hotelService.getHotelById(hotel.getId_hotel());
            if (existingHotel == null) {
                return ResponseEntity.status(404)
                        .body(new ResponseHotelDto(404, "Hotel not found"));
            }
            HotelModel existingHotelByEmail =
                    hotelService.findByEmail(hotel.getEmail());
            if (existingHotelByEmail != null
                    && existingHotelByEmail.getId_hotel() != hotel.getId_hotel()) {
                return ResponseEntity.status(400)
                        .body(new ResponseHotelDto(400, "Hotel with this " +
                                "email already exists"));
            }
            existingHotel.setName(hotel.getName().trim());
            existingHotel.setEmail(hotel.getEmail().trim());
            existingHotel.setAddress(hotel.getAddress().trim());
            existingHotel.setPhone(hotel.getPhone().trim());
            existingHotel.setRating(hotel.getRating().floatValue());
            existingHotel.setDescription(hotel.getDescription().trim());
            existingHotel.setStatus(1);
            hotelService.updateHotel(existingHotel);
            return ResponseEntity.status(200)
                    .body(new ResponseHotelDto(200, "Success update hotel"));
        } catch (Exception error) {
            return ResponseEntity.status(500).body(
                    new ResponseHotelDto(500,
                            "Internal server error: " + error.getMessage()));
        }
    }

    @PostMapping("/deleteHotel")
    public ResponseEntity<ResponseHotelDto> deleteHotel(
            @RequestBody @Valid RequestGetHotelByIdDto id_hotel,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append(", ");
                });
                return ResponseEntity.status(400)
                        .body(new ResponseHotelDto(400,
                                errorMessage.toString()));
            }
            HotelModel existingHotel =
                    hotelService.getHotelById(id_hotel.getId_hotel());
            if (existingHotel == null) {
                return ResponseEntity.status(404)
                        .body(new ResponseHotelDto(404, "Hotel not found"));
            }
            existingHotel.setStatus(0);
            int result = hotelService.deleteHotel(existingHotel.getId_hotel(),
                    existingHotel.getStatus());
            if (result == 0) {
                return ResponseEntity.status(400)
                        .body(new ResponseHotelDto(400, "Failed to delete " +
                                "hotel"));
            }
            return ResponseEntity.status(200)
                    .body(new ResponseHotelDto(200, "Success delete hotel"));
        } catch (Exception error) {
            return ResponseEntity.status(500).body(
                    new ResponseHotelDto(500,
                            "Internal server error: " + error.getMessage()));
        }
    }

    @PostMapping("/reactivateHotel")
    public ResponseEntity<ResponseHotelDto> reactivateHotel(
            @RequestBody @Valid RequestGetHotelByIdDto id_hotel,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append(", ");
                });
                return ResponseEntity.status(400)
                        .body(new ResponseHotelDto(400,
                                errorMessage.toString()));
            }
            HotelModel existingHotel =
                    hotelService.getHotelById(id_hotel.getId_hotel());
            if (existingHotel == null) {
                return ResponseEntity.status(404)
                        .body(new ResponseHotelDto(404, "Hotel not found"));
            }
            existingHotel.setStatus(1);
            int result =
                    hotelService.reactivateHotel(existingHotel.getId_hotel(),
                    existingHotel.getStatus());
            if (result == 0) {
                return ResponseEntity.status(400)
                        .body(new ResponseHotelDto(400, "Failed to reactivate" +
                                " hotel"));
            }
            return ResponseEntity.status(200)
                    .body(new ResponseHotelDto(200, "Success reactivate " +
                            "hotel"));
        } catch (Exception error) {
            return ResponseEntity.status(500).body(
                    new ResponseHotelDto(500,
                            "Internal server error: " + error.getMessage()));
        }
    }

}
