package com.valrazi.cinemadb.model;

import com.valrazi.cinemadb.utils.Uuid;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @Column(name = "email", updatable = false)
    private String email;

    @Column(name = "username", unique = true)
    private String uname;

    @Column(name = "password")
    private String password;
}
