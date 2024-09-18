package com.hoteleria_app.hoteleria_app.model.UserPermissionRol;

import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.model.PermissionRol.PermissionRolModel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_permission_rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionRolModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_permission_rol")
    private Long id_user_permission_rol;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id_user")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "id_permission_rol", nullable = false, referencedColumnName = "id_permission_rol")
    private PermissionRolModel permissionRol;

}
