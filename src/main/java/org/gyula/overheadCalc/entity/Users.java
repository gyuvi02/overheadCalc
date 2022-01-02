package org.gyula.overheadCalc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @OneToMany(mappedBy = "username", cascade = CascadeType.ALL)
    private List<Authorities> authorities;

    @OneToMany(mappedBy = "userName", cascade = CascadeType.ALL)
    private List<UserImages> userImages;

    public Users() {
    }

    public Users(String username, String password, int enabled, List<Authorities> authorities, List<UserImages> userImages) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
        this.userImages = userImages;
    }

    //    public Users(String username, String password, int enabled) {
//        this.username = username;
//        this.password = password;
//        this.enabled = enabled;
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

    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }

    public List<UserImages> getUserImages() {
        return userImages;
    }

    public void setUserImages(List<UserImages> userImages) {
        this.userImages = userImages;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                ", userImages=" + userImages +
                '}';
    }
}
