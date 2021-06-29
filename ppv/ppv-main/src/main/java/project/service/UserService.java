package project.service;

import project.controller.representation.UserRepresentation;
import project.controller.representation.WebcontentRepresentation;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserRepresentation> findAll();

    Optional<UserRepresentation> findById(Long id);

    Optional<UserRepresentation> findByUsername(String username);

    void save(UserRepresentation userRepresentation);

    void view(WebcontentRepresentation webcontentRepresentation, UserRepresentation userRepresentation);

    void withdrawal(UserRepresentation userRepresentation);

    void removeUser(Long id);

}
