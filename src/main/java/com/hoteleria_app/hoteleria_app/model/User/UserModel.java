package com.hoteleria_app.hoteleria_app.model.User;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false)
    private Long id;

    private String name;
    private String lastname;
    private String email;
    private String phone;

}
