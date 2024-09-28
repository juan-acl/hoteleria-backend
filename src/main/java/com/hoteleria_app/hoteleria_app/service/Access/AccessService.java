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

}
