package org.gyula.overheadCalc.entity;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Table(name = "user_image")
public class UserImages {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Lob
    @Column(name = "usersImage", length = 16777215)
    private byte[] userImage;

    @OneToOne
    @JoinColumn(name = "userName", referencedColumnName = "username", insertable = false, updatable = false)
    private Users users;

    public UserImages() {
    }

    public UserImages(String userName, byte[] userImage, Users users) {
        this.userName = userName;
        this.userImage = userImage;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getUsersImage() {
        return userImage;
    }

    public void setUsersImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
