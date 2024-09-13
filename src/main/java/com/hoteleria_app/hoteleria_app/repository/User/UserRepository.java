package com.hoteleria_app.hoteleria_app.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    /*
     * Find a user by email
     * 
     * @param email
     * 
     * @return UserModel
     * The sql query is generated automatically by spring boot
     */
    UserModel findByEmail(String email);
}
