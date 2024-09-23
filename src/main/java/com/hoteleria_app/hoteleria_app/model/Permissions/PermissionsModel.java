package com.hoteleria_app.hoteleria_app.model.Permissions;

import com.hoteleria_app.hoteleria_app.model.User.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permisos")
public class PermissionsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso", nullable = false)
    private Long id_permiso;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int delete;

    @Column(nullable = false)
    private int update;

    @Column(nullable = false)
    private int create;

    @Column(nullable = false)
    private int view;

    @Column(nullable = false)
    private int report;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private UserModel user;

}
