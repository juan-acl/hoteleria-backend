package com.hoteleria_app.hoteleria_app.model.Room;

import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;
import com.hoteleria_app.hoteleria_app.model.RoomType.RoomTypeModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "room")
public class RoomModel {
    @Id
    @Column(name = "id_room", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;

    @NotNull
    @Column(name = "available", nullable = false)
    private Byte available;

    @NotNull
    @Size(max = 500)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "active", nullable = false)
    private Integer active;

    @NotNull
    @Column(name = "price", nullable = false)
    private Float price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_room_type", nullable = false)
    private RoomTypeModel idRoomType;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_hotel", nullable = false)
    private HotelModel idHotel;

}