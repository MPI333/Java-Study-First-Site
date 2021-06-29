package project.service;

import project.controller.representation.UserRepresentation;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserRepresentation> findAll();

    Optional<UserRepresentation> findById(Long id);

    void save(UserRepresentation userRepresentation);

    void removeUser(Long id);

}
