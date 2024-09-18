package com.hoteleria_app.hoteleria_app.model.Rol;

import com.hoteleria_app.hoteleria_app.model.PermissionRol.PermissionRolModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "idRol")
    private Set<PermissionRolModel> permissionRols = new LinkedHashSet<>();

}
