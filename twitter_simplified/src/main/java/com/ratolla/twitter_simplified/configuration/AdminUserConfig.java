package com.ratolla.twitter_simplified.configuration;

import com.ratolla.twitter_simplified.enitities.Role;
import com.ratolla.twitter_simplified.enitities.User;
import com.ratolla.twitter_simplified.repositories.RoleRepository;
import com.ratolla.twitter_simplified.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminUserConfig(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Admin já existe");
                },
                ()-> {
                    var user = new User();
                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                }
        );


    }
}
