package com.ideationstorm.com.ideationstorm.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ideationstorm.com.ideationstorm.language.LanguageEntity;
import com.ideationstorm.com.ideationstorm.project.ProjectEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;

    private String password;

    private int permission;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name="created_datetime")
    @CreationTimestamp
    private LocalDateTime createdDatetime;
    @Column(name="updated_datetime")
    private LocalDateTime updatedDatetime;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<ProjectEntity> project;

    @Column(unique = true)
    public void setUsername(String username) {
        this.username = username;
    }
    @Column(unique = true)
    public void setEmail(String email){
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public Set<ProjectEntity> getProject() {
        return project;
    }

    public void setProject(Set<ProjectEntity> project) {
        this.project = project;
    }

//    @Override
//    public String toString() {
//        return String.format("User{id=%d, username=%s, email=%s}", id, username,email);
//    }

}
