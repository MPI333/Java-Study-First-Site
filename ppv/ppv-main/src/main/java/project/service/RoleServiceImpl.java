package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.controller.representation.RoleRepresentation;
import project.model.Role;
import project.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleRepresentation> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleRepresentation::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleRepresentation> findById(Long id) {
        return roleRepository.findById(id).map(RoleRepresentation::new);
    }

    @Override
    public void save(RoleRepresentation roleRepresentation) {
        Role role = new Role();
        role.setId(roleRepresentation.getId());
        role.setName(roleRepresentation.getName());
        role.setUsers(roleRepresentation.getUsers());
        roleRepository.save(role);
    }

    @Override
    public void removeRole(Long id) {
        roleRepository.deleteById(id);
    }

}
