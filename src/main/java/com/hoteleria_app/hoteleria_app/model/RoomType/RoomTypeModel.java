package com.hoteleria_app.hoteleria_app.model.RoomType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;

@Getter
@Setter
@Entity
@Table(name = "room_type")
public class RoomTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room_type", nullable = false)
    private Integer id_room_type;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 200)
    @NotNull
    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Size(max = 200)
    @Column(name = "amenities", length = 200)
    private String amenities;

    @NotNull
    @Column(name = "maximum_people", nullable = false)
    private Integer maximumPeople;

    @JsonBackReference
    @OneToMany(mappedBy = "idRoomType", fetch = FetchType.EAGER)
    private Set<RoomModel> rooms = new LinkedHashSet<>();

}
