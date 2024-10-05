package com.hoteleria_app.hoteleria_app.model.Hotel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hoteleria_app.hoteleria_app.model.Access.AccessModel;
import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;

@Getter
@Setter
@Entity
@Table(name = "hotel")
public class HotelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel", nullable = false)
    private Long id_hotel;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 50)
    @NotNull
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Size(max = 100)
    @NotNull
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Size(max = 10)
    @NotNull
    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @NotNull
    @Column(name = "rating", nullable = false)
    private Float rating;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @JsonBackReference
    @OneToMany(mappedBy = "idHotel")
    private Set<AccessModel> accesses = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "idHotel")
    private Set<RoomModel> rooms = new LinkedHashSet<>();

}
