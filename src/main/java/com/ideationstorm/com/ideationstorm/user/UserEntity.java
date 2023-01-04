package com.ideationstorm.com.ideationstorm.user;

import com.ideationstorm.com.ideationstorm.language.LanguageEntity;
import com.ideationstorm.com.ideationstorm.project.ProjectEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String email;

    private int permission;


    @OneToMany(mappedBy = "user")
    private Set<ProjectEntity> project;
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

//    @Override
//    public String toString() {
//        return String.format("User{id=%d, username=%s, email=%s}", id, username,email);
//    }

}