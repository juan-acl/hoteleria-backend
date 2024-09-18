package com.hoteleria_app.hoteleria_app.model.PermissionRol;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.hoteleria_app.hoteleria_app.model.Permission.PermissionModel;
import com.hoteleria_app.hoteleria_app.model.Rol.RolModel;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;

import com.hoteleria_app.hoteleria_app.model.UserPermissionRol.UserPermissionRolModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "permission_rol")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PermissionRolModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission_rol", nullable = false)
    private Long id_permission_rol;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission_rol", joinColumns = @JoinColumn(name = "id_permission_rol", referencedColumnName = "id_permission_rol"), inverseJoinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id_user"))
    private Set<UserModel> users = new HashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_permission", nullable = false)
    private PermissionModel idPermission;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    private RolModel idRol;

    @OneToMany(mappedBy = "permissionRol")
    private Set<UserPermissionRolModel> userPermissionRols = new LinkedHashSet<>();

    public PermissionModel getPermission() {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPermission'");
    }

    // Many-to-One relationship with PermissionModel
    // @ManyToOne
    // @JoinColumn(name = "id_permission", nullable = false, referencedColumnName =
    // "id_permission")
    // private PermissionModel permission;

    // Many-to-One relationship with RolModel
    // @ManyToOne
    // @JoinColumn(name = "id_rol", nullable = false, referencedColumnName =
    // "id_rol")
    // private RolModel rol;

}