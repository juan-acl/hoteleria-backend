package com.hoteleria_app.hoteleria_app.repository.User;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hoteleria_app.hoteleria_app.model.User.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmail(@Param("email") String email);

    List<UserModel> findAll();

}
