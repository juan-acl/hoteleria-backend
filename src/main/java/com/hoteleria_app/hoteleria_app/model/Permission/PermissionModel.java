package com.hoteleria_app.hoteleria_app.model.Permission;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.hoteleria_app.hoteleria_app.model.PermissionRol.PermissionRolModel;

import lombok.*;

@Entity
@Table(name = "permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission", nullable = false)
    private Long id_permission;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "idPermission")
    private Set<PermissionRolModel> permissionRols = new LinkedHashSet<>();

}
