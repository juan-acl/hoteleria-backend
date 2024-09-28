package com.hoteleria_app.hoteleria_app.repository.Access;

import com.hoteleria_app.hoteleria_app.model.Access.AccessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AccessRepository extends JpaRepository<AccessModel, Long> {
    @Query("SELECT a FROM AccessModel a WHERE a.idUser.id_user = :id_user")
    Set<AccessModel> findAccesByIdUser(@Param("id_user") Long id_user);
}
