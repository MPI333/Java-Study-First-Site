package project.service;

import project.controller.representation.RoleRepresentation;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<RoleRepresentation> findAll();

    Optional<RoleRepresentation> findById(Long id);

    void save(RoleRepresentation userRepresentation);

    void removeRole(Long id);

}
