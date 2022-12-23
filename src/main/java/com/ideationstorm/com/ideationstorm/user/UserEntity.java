package com.ideationstorm.com.ideationstorm.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "IDEATION_STORM")
public class UserEntity {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String email;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, username=%s, email=%s}", id, username,email);
    }
}
