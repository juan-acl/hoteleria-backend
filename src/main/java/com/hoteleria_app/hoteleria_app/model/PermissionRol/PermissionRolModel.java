package com.hoteleria_app.hoteleria_app.model.PermissionRol;

import com.hoteleria_app.hoteleria_app.model.Permission.PermissionModel;
import com.hoteleria_app.hoteleria_app.model.Rol.RolModel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permission_rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRolModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission_rol", nullable = false)
    private Long id_permission_rol;

    @ManyToOne
    @JoinColumn(name = "id_permission", nullable = false)
    private PermissionModel permission;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolModel rol;
}