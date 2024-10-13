package com.hoteleria_app.hoteleria_app.service.Reservation;

import com.hoteleria_app.hoteleria_app.dto.Reservation.ResponseCreateReservationForHtml;
import com.hoteleria_app.hoteleria_app.dto.Reservation.RoomReservation;
import com.hoteleria_app.hoteleria_app.dto.Reservation.RoomsDtoForEmail;
import com.hoteleria_app.hoteleria_app.model.Reservation.ReservationModel;
import com.hoteleria_app.hoteleria_app.model.ReservationDetail.ReservationDetailModel;
import com.hoteleria_app.hoteleria_app.model.Room.RoomModel;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.repository.Reservation.ReservationRepository;
import com.hoteleria_app.hoteleria_app.service.ReservationDetail.ReservationDetailService;
import com.hoteleria_app.hoteleria_app.service.Room.RoomService;
import com.hoteleria_app.hoteleria_app.service.User.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final String STATUS_RESERVATION = "CONFIRMED";
    private final Float INITIAL_TOTAL_PRICE = 0.0f;

    private final ReservationRepository reservationRepository;
    private final RoomService roomService;
    private final ReservationDetailService reservationDetailService;
    private final UserService userService;

    public ReservationService(UserService userService,
                              ReservationRepository reservationRepository,
                              RoomService roomService,
                              ReservationDetailService reservationDetailService) {
        this.reservationRepository = reservationRepository;
        this.roomService = roomService;
        this.reservationDetailService = reservationDetailService;
        this.userService = userService;
    }

    public ReservationModel findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public ReservationModel createReservation(ReservationModel reservation) {
        return reservationRepository.save(reservation);
    }

    public ReservationModel updateReservation(ReservationModel reservation) {
        return reservationRepository.save(reservation);
    }

    /**
     * Crea una reserva para un usuario con las habitaciones especificadas.
     * Este método es transaccional, lo que significa que si ocurre alguna
     * excepción durante el proceso,
     * todos los cambios se revertirán para mantener la integridad de los datos.
     *
     * @param id_user El ID del usuario que realiza la reserva.
     * @param rooms   Una lista de reservas de habitaciones que contiene los
     *                detalles de cada habitación a reservar.
     * @return true si la reserva se crea correctamente, false en caso
     * contrario.
     * @throws RuntimeException si ocurre algún error durante el proceso de
     *                          reserva.
     */
    @Transactional
    public ResponseCreateReservationForHtml createReservation(
            Long id_user,
            List<RoomReservation> rooms
    ) {
        try {
            UserModel user = userService.findById(id_user);
            if (user == null) {
                throw new RuntimeException("User not found");
            }

            List<ReservationDetailModel> detailReservation =
                    new ArrayList<ReservationDetailModel>();
            List<RoomsDtoForEmail> RoomsDtoForEmail =
                    new ArrayList<RoomsDtoForEmail>();
            ReservationModel reservation = new ReservationModel();
            reservation.setIdUser(user);
            reservation.setStatus(STATUS_RESERVATION);
            reservation.setTotal(INITIAL_TOTAL_PRICE);
            reservation.setEmitionDate(LocalDateTime.now());
            ReservationModel idReservation = createReservation(reservation);
            ResponseCreateReservationForHtml roomInfoForEmail =
                    new ResponseCreateReservationForHtml();
            Float total = INITIAL_TOTAL_PRICE;

            for (RoomReservation roomReservation : rooms) {

                //verifica si la fecha final es después de la fecha inicial
                if (!roomReservation.getFinal_reservation_date().isAfter(roomReservation.getInitial_reservation_date())) {
                    throw new RuntimeException("Final date must be after initial date.");
                }

                // verifica si la reserva es de al menos una noche
                Long daysBetween =
                        ChronoUnit.DAYS.between(roomReservation.getInitial_reservation_date().toLocalDate(), roomReservation.getFinal_reservation_date().toLocalDate());
                if (daysBetween < 1) {
                    throw new RuntimeException("You must reserve at least one night.");
                }

                // Verifica si la fecha inicial de cada reserva es antes de
                // la fecha actual
                if (roomReservation.getInitial_reservation_date().isBefore(LocalDateTime.now())) {
                    throw new RuntimeException("Initial date must be after " +
                            "current date");
                }

                RoomModel findRoom =
                        roomService.findRoomById(roomReservation.getId_room());
                if (findRoom == null) {
                    throw new RuntimeException("Room not found");
                }
                RoomsDtoForEmail.add(new RoomsDtoForEmail(roomReservation.getInitial_reservation_date(),
                        roomReservation.getFinal_reservation_date(),
                        findRoom.getPrice(),
                        findRoom.getRoomNumber()));
                Long isReservedRoom =
                        roomService.countReservedRoom(roomReservation.getId_room(), roomReservation.getInitial_reservation_date(), roomReservation.getFinal_reservation_date());
                if (isReservedRoom > 0) {
                    throw new RuntimeException("Room is reserved in this date");
                }
                Float subTotal = findRoom.getPrice() * daysBetween;
                total += subTotal;
                ReservationDetailModel reservationDetail =
                        new ReservationDetailModel();
                reservationDetail.setIdReservation(idReservation);
                reservationDetail.setIdRoom(findRoom);
                reservationDetail.setInitialReservationDate(roomReservation.getInitial_reservation_date());
                reservationDetail.setFinalReservationDate(roomReservation.getFinal_reservation_date());
                reservationDetail.setPrice(findRoom.getPrice());
                detailReservation.add(reservationDetail);
            }
            idReservation.setTotal(total);
            updateReservation(idReservation);
            reservationDetailService.createBatchDetailReservations(detailReservation);
            roomInfoForEmail.setTotalPrice(total);
            roomInfoForEmail.setRoomsDtoForEmails(RoomsDtoForEmail);
            return roomInfoForEmail;
        } catch (Exception error) {
            throw new RuntimeException("Error creating reservation: " + error.getMessage());
        }
    }

}
