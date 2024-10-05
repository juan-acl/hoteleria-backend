package com.hoteleria_app.hoteleria_app.service.Access;

import com.hoteleria_app.hoteleria_app.model.Access.AccessModel;
import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.repository.Access.AccessRepository;
import com.hoteleria_app.hoteleria_app.repository.Hotel.HotelRepository;
import com.hoteleria_app.hoteleria_app.repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

@Service
public class AccessService {
  private final AccessRepository accessRepository;
  private final HotelRepository hotelRepository;
  private final UserRepository userRepository;

  public AccessService (AccessRepository accessRepository, HotelRepository hotelRepository, UserRepository userRepository) {
    this.accessRepository = accessRepository;
    this.hotelRepository = hotelRepository;
    this.userRepository = userRepository;
  }

  public Set<AccessModel> getAllAccesByIdUser(Long id_user) {
    return accessRepository.findAccesByIdUser(id_user);
  }

  public AccessModel getAccessById(Long id_access) {
    return accessRepository.findById(id_access).orElse(null);
  }

  public Boolean deleteAccessByIdUser(Long id_user) {
    try {
      accessRepository.deleteById(id_user);
      return true;
    }catch (Exception error) {
      return false;
    }
  }

  public String assignmentAccessUserByHotel(List<Long> ids_hotel, Long id_user) {
    try {
      List<AccessModel> accessList = new ArrayList<>();
      ids_hotel.forEach(id_hotel -> {
        HotelModel hotelExist = hotelRepository.findById(id_hotel).orElse(null);
        UserModel userExist = userRepository.findById(id_user).orElse(null);
        AccessModel accessCreate = new AccessModel();
        accessCreate.setIdHotel(hotelExist);
        accessCreate.setIdUser(userExist);
        accessList.add(accessCreate);
      });
      accessRepository.saveAll(accessList);
      return "";
    }catch (Exception error) {
      return error.getMessage();
    }
  }

}
