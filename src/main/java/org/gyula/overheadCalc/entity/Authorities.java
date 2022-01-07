package org.gyula.overheadCalc.entity;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Slf4j
@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "authority")
    private String authority;

    @Column(name = "username")
    private String username;

    @OneToOne(mappedBy = "authorities")
    private Users users;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "username")
//    private Users users;

    public Authorities() {
    }

    public Authorities(String authority, Users users) {
        this.authority = authority;
        this.username = users.getUsername();
        this.users = users;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "id='" + id + '\'' +
                ", authority='" + authority + '\'' +
                ", users=" + users +
                '}';
    }
}
