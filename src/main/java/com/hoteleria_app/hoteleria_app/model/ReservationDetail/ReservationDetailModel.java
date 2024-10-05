package com.hoteleria_app.hoteleria_app.model.ReservationDetail;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.hoteleria_app.hoteleria_app.model.Reservation.ReservationModel;
import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;

@Getter
@Setter
@Entity
@Table(name = "reservation_detail")
public class ReservationDetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation_detail", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "initial_reservation_date", nullable = false)
    private LocalDateTime initialReservationDate;

    @NotNull
    @Column(name = "final_reservation_date", nullable = false)
    private LocalDateTime finalReservationDate;

    @NotNull
    @Column(name = "price", nullable = false)
    private Float price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_reservation", nullable = false)
    private ReservationModel idReservation;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_room", nullable = false)
    private RoomModel idRoom;

}