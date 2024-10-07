package com.hoteleria_app.hoteleria_app.service.ReservationDetail;

import com.hoteleria_app.hoteleria_app.model.ReservationDetail.ReservationDetailModel;
import com.hoteleria_app.hoteleria_app.repository.ReservationDetail.ReservationDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationDetailService {
    private final ReservationDetailRepository reservationDetailRepository;
    public ReservationDetailService(ReservationDetailRepository reservationDetailRepository) {
        this.reservationDetailRepository = reservationDetailRepository;
    }

    public ReservationDetailModel createReservationDetail(ReservationDetailModel reservationDetail) {
        return reservationDetailRepository.save(reservationDetail);
    }

    public ReservationDetailModel findById(Long id) {
        return reservationDetailRepository.findById(id).orElse(null);
    }

    public void createBatchDetailReservations(List<ReservationDetailModel> reservationDetails) {
            reservationDetailRepository.saveAll(reservationDetails);
    }

}
