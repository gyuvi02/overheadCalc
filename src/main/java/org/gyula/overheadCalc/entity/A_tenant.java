package org.gyula.overheadCalc.entity;

import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="a_tenant")
public class A_tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "theTenant")
    private List<A_flat> flats;

    public A_tenant() {
    }

//    public A_tenant(String firstName, String lastName, String email, String phone) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.phone = phone;
//    }


    public A_tenant(@NonNull String firstName, @NonNull String lastName, @NonNull String email, String phone, List<A_flat> flats) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.flats = flats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<A_flat> getFlats() {
        return flats;
    }

    public void setFlats(List<A_flat> flats) {
        this.flats = flats;
    }

    @Override
    public String toString() {
        return "A_tenant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
