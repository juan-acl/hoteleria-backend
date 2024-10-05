package com.hoteleria_app.hoteleria_app.repository.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Long> {
    List<HotelModel> findAll();

    HotelModel findByEmail(String email);

    @Modifying
    @Query("UPDATE HotelModel h SET h.status = :status WHERE h.id_hotel = :id_hotel")
    int updateStatus(@Param("id_hotel") Long id_hotel, @Param("status") Integer status);
}
