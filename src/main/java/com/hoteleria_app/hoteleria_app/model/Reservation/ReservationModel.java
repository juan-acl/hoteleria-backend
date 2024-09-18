package com.hoteleria_app.hoteleria_app.model.Reservation;

import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class ReservationModel {
    @Id
    @Column(name = "id_reservation", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "emition_date", nullable = false)
    private LocalDate emitionDate;

    @Size(max = 50)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @NotNull
    @Column(name = "total", nullable = false)
    private Float total;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private UserModel idUser;

}