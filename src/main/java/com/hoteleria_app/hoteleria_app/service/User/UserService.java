package com.hoteleria_app.hoteleria_app.service.User;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hoteleria_app.hoteleria_app.model.Access.AccessModel;
import com.hoteleria_app.hoteleria_app.model.Permission.PermissionModel;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.repository.User.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel updateUser(UserModel user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserModel findById(Long id) {
                        return userRepository.findById(id).orElse(null);
    }

    public Set<PermissionModel> findByIdWithPermissions(Long id) {
        return userRepository.findPermisosByUserId(id);
    }

    public Set<AccessModel> findAccessByUserId(Long id) {
        return userRepository.findAccessByUserId(id);
    }

}
