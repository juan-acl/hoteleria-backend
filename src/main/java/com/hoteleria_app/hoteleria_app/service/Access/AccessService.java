package com.hoteleria_app.hoteleria_app.service.Access;

import com.hoteleria_app.hoteleria_app.model.Access.AccessModel;
import com.hoteleria_app.hoteleria_app.repository.Access.AccessRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AccessService {
  private final AccessRepository accessRepository;

  public AccessService (AccessRepository accessRepository) {
    this.accessRepository = accessRepository;
  }

  public Set<AccessModel> getAllAccesByIdUser(Long id_user) {
    return accessRepository.findAccesByIdUser(id_user);
  }

  public AccessModel getAccessById(Long id_access) {
    return accessRepository.findById(id_access).orElse(null);
  }

  public int deleteAccessByIdUser(Long id_user) {
    try {
      accessRepository.deleteById(id_user);
      return 1;
    }catch (Exception error) {
      return 0;
    }
  }

}
