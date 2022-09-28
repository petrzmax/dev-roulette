package com.devroulette.restapi.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    private Long id;
    private String username;
    private String email;
    private boolean premium;
    private long balance;
}
