package com.hoteleria_app.hoteleria_app.repository.ReservationDetail;

import com.hoteleria_app.hoteleria_app.model.ReservationDetail.ReservationDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDetailRepository extends JpaRepository<ReservationDetailModel, Long> {
    ReservationDetailModel findById(long id);
}

