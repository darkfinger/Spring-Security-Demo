package com.dkkcorp.sprinnboot.springsecuritydemo.config;

import com.dkkcorp.sprinnboot.springsecuritydemo.model.User;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
