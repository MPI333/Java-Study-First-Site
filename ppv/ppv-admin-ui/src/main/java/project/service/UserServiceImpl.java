package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.controller.representation.UserRepresentation;
import project.model.User;
import project.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserRepresentation> findAll() {
        return userRepository.findAll().stream()
                .map(UserRepresentation::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRepresentation> findById(Long id) {
        return userRepository.findById(id).map(UserRepresentation::new);
    }

    @Override
    public void save(UserRepresentation userRepresentation) {
        User user = new User();
        user.setId(userRepresentation.getId());
        user.setUsername(userRepresentation.getUsername());
        user.setPassword(passwordEncoder.encode(userRepresentation.getPassword()));
        user.setEmail(userRepresentation.getEmail());
        user.setBalance(userRepresentation.getBalance());
        user.setRoles(userRepresentation.getRoles());
        user.setWebcontent(userRepresentation.getWebcontent());
        userRepository.save(user);
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

}
