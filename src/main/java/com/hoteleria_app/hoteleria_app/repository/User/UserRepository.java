package com.hoteleria_app.hoteleria_app.repository.User;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hoteleria_app.hoteleria_app.model.Permission.PermissionModel;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmail(@Param("email") String email);

    List<UserModel> findAll();

    // El nombre de la tabla siempre debe ser el mismo que el de la clase
    @Query("SELECT p FROM PermissionModel p WHERE p.user.id_user = :id_user")
    Set<PermissionModel> findPermisosByUserId(@Param("id_user") Long id_user);

}
