package com.hoteleria_app.hoteleria_app.repository.User;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.hoteleria_app.hoteleria_app.model.User.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmail(String email);
}
