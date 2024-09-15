package com.hoteleria_app.hoteleria_app.model.Permission;

import jakarta.persistence.*;
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
}
