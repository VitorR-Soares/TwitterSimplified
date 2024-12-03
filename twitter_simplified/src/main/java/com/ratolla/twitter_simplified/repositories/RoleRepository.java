package com.ratolla.twitter_simplified.repositories;

import com.ratolla.twitter_simplified.enitities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
