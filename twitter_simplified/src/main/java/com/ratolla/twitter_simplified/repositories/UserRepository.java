package com.ratolla.twitter_simplified.repositories;

import com.ratolla.twitter_simplified.enitities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

}
