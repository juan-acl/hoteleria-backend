package com.hoteleria_app.hoteleria_app.repository.RoomType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hoteleria_app.hoteleria_app.model.RoomType.RoomTypeModel;
import java.util.List;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomTypeModel, Long> {

    List<RoomTypeModel> findAll();
}
