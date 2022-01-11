package org.gyula.overheadCalc.entity;

import lombok.extern.slf4j.Slf4j;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Slf4j
@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotNull(message = "Please enter username")
    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 20, message = "Minimum 4, maximum 20 characters")
//    @UniqueElements
    @Column(name = "username")
    private String username;

    @NotNull(message = "Please enter a password")
    @Size(min = 6, max = 20)
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "enabled")
    private int enabled = 1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auth_id", referencedColumnName = "id")
    private Authorities authorities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private A_tenant tenant;

    public Users() {
    }

    public Users(String username, String password, int enabled, Authorities authorities, A_tenant tenant) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
        this.tenant = tenant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public A_tenant getTenant() {
        return tenant;
    }

    public void setTenant(A_tenant tenant) {
        this.tenant = tenant;
    }
}
