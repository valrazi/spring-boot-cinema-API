package com.valrazi.cinemadb.repository;

import com.valrazi.cinemadb.model.ERole;
import com.valrazi.cinemadb.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
