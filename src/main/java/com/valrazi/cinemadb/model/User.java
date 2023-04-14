package com.valrazi.cinemadb.model;

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
    @Column(name = "username")
    private String uname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
