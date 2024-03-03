package com.mritun.springsecurity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean active;

    private String roles;

    private String permissions;

    public User(String username, String password, String roles, String permissions) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active=true;
    }

    protected User(){

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
    public List<String> getRoleList(){
        if(!this.roles.isEmpty()){
            return Arrays.asList(roles.split(","));
        }else
          return   new ArrayList<>();
    }
    public List<String> getPermissionList(){
        if(!this.permissions.isEmpty()){
            return Arrays.asList(permissions.split(","));
        }else
            return   new ArrayList<>();
    }
}
