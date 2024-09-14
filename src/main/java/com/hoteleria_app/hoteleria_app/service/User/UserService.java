package com.hoteleria_app.hoteleria_app.service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteleria_app.hoteleria_app.dto.UserDto.UserRequestCreateUser;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.repository.User.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel createUser(UserRequestCreateUser userRequest) {
        System.out.println(userRequest);
        UserModel userModel = new UserModel();
        userModel.setName(userRequest.getName());
        userModel.setLastname(userRequest.getLastname());
        userModel.setEmail(userRequest.getEmail());
        userModel.setPhone(userRequest.getPhone());

        return userRepository.save(userModel);
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

}
