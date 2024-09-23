package com.hoteleria_app.hoteleria_app.repository.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Long> {
    List<HotelModel> findAll();
}
