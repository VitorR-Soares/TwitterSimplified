package com.ratolla.twitter_simplified.controllers;

import com.ratolla.twitter_simplified.controllers.dto.CreateUserDTO;
import com.ratolla.twitter_simplified.enitities.Role;
import com.ratolla.twitter_simplified.enitities.User;
import com.ratolla.twitter_simplified.repositories.RoleRepository;
import com.ratolla.twitter_simplified.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@Transactional
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/createUser")
    public ResponseEntity<Void> saveUser(@RequestBody CreateUserDTO dto){

        Optional<User> userFromDB = userRepository.findByUsername(dto.username());
        if (userFromDB.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Role roleBasic = roleRepository.findByName(Role.Values.BASIC.name());

        User newUser = new User();
        newUser.setUsername(dto.username());
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        newUser.setRoles(Set.of(roleBasic));
        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/listUsers")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<List<User>> listUsers(){

        var users = userRepository.findAll();

        return ResponseEntity.ok(users);

    }
}
