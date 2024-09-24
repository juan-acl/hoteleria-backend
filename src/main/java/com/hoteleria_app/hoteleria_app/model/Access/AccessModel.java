package com.hoteleria_app.hoteleria_app.model.Access;

import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name = "access")
public class AccessModel {
    @Id
    @Column(name = "id_access", nullable = false)
    private Integer id;

    @JsonBackReference
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private UserModel idUser;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_hotel", nullable = false)
    private HotelModel idHotel;

}
