package com.valrazi.cinemadb.repository;

import com.valrazi.cinemadb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u where u.uname = ?1")
    Optional<User> findByUsername(String username);
}
