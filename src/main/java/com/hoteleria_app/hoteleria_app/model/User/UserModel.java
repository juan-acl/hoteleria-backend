package com.hoteleria_app.hoteleria_app.model.User;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.hoteleria_app.hoteleria_app.model.UserPermissionRol.UserPermissionRolModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")

public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Long id_user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<UserPermissionRolModel> userPermissionRols = new HashSet<>();
}
