package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.controller.representation.UserRepresentation;
import project.controller.representation.WebcontentRepresentation;
import project.model.User;
import project.model.Webcontent;
import project.repository.UserRepository;
import project.repository.WebcontentRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    public Optional<UserRepresentation> findByUsername(String username) {
        return userRepository.findByUsername(username).map(UserRepresentation::new);
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
    public void view(WebcontentRepresentation webcontentRepresentation, UserRepresentation userRepresentation) {
        Webcontent webcontent = new Webcontent();
        webcontent.setId(webcontentRepresentation.getId());
        webcontent.setIco(webcontentRepresentation.getIco());
        webcontent.setPlatform(webcontentRepresentation.getPlatform());
        webcontent.setTitle(webcontentRepresentation.getTitle());
        webcontent.setAmount(webcontentRepresentation.getAmount());
        webcontent.setLink(webcontentRepresentation.getLink());
        webcontent.setUsers(webcontentRepresentation.getUsers());

        User user = new User();
        user.setId(userRepresentation.getId());
        user.setUsername(userRepresentation.getUsername());
        user.setPassword(userRepresentation.getPassword());
        user.setEmail(userRepresentation.getEmail());

        BigDecimal balance = userRepresentation.getBalance();
        balance = balance.add(new BigDecimal("0.01"));
        user.setBalance(balance);

        user.setRoles(userRepresentation.getRoles());

        List<Webcontent> wc = new ArrayList<>(userRepresentation.getWebcontent());
        wc.add(webcontent);
        user.setWebcontent(wc);
        userRepository.save(user);
    }

    @Override
    public void withdrawal(UserRepresentation userRepresentation) {
        User user = new User();
        user.setId(userRepresentation.getId());
        user.setUsername(userRepresentation.getUsername());
        user.setPassword(userRepresentation.getPassword());
        user.setEmail(userRepresentation.getEmail());
        user.setBalance(new BigDecimal("0.00"));
        user.setRoles(userRepresentation.getRoles());
        user.setWebcontent(userRepresentation.getWebcontent());
        userRepository.save(user);
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

}
