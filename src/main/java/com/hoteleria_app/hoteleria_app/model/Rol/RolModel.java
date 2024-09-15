package com.hoteleria_app.hoteleria_app.model.Rol;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

import com.hoteleria_app.hoteleria_app.model.PermissionRol.PermissionRolModel;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rol")
public class RolModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", nullable = false)
    private Long id_rol;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "rol")
    private Set<PermissionRolModel> rolePermissions = new HashSet<>();
}
