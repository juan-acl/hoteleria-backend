package com.hoteleria_app.hoteleria_app.model.User;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hoteleria_app.hoteleria_app.model.UserPermissionRol.UserPermissionRolModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")

public class UserModel implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return java.util.Collections.emptyList();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }
}
