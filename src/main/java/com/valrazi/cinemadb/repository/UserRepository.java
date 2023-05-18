package com.valrazi.cinemadb.repository;

import com.valrazi.cinemadb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUname(String username);

    Boolean existsByUname(String username);

    Boolean existsByEmail(String email);
}
