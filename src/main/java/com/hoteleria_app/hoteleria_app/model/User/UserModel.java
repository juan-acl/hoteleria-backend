package com.hoteleria_app.hoteleria_app.model.User;

import com.hoteleria_app.hoteleria_app.model.Access.AccessModel;
import com.hoteleria_app.hoteleria_app.model.Permisos.PermisosModel;
import com.hoteleria_app.hoteleria_app.model.Reservation.ReservationModel;
import com.hoteleria_app.hoteleria_app.model.UserPermissionRol.UserPermissionRolModel;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idUser")
    private Set<AccessModel> accesses = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<PermisosModel> permisos = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idUser")
    private Set<ReservationModel> reservations = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserPermissionRolModel> userPermissionRols = new LinkedHashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permisos.stream()
                .map(permiso -> new SimpleGrantedAuthority(permiso.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
