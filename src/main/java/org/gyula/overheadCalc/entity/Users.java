package org.gyula.overheadCalc.entity;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotNull
    @Column(name = "username", nullable = false, unique = false)
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "enabled")
    private int enabled = 1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auth_id", referencedColumnName = "id")
    private Authorities authorities;

//    @OneToMany(mappedBy = "username")
//    private List<Authorities> authorities;
//
//    @OneToMany(mappedBy = "userName", cascade = CascadeType.ALL)
//    private List<UserImages> userImages;

    public Users() {
    }

    public Users(String username, String password, int enabled, Authorities authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    //    public Users(String username, String password, int enabled) {
//        this.username = username;
//        this.password = password;
//        this.enabled = enabled;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Authorities getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authorities authorities) {
        this.authorities = authorities;
    }
}
