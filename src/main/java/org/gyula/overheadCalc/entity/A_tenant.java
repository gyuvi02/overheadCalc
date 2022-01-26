package org.gyula.overheadCalc.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="a_tenant")
public class A_tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @Column(name = "email")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Not an acceptable email format")
    private String email;

    @Column(name = "phone")
    @Digits(fraction = 0, integer = 20, message = "Use digits only for the phone number")
    private String phone;

    @OneToMany(mappedBy = "theTenant")
    private List<A_flat> flats;

    @OneToOne(mappedBy = "tenant")
    private Users user;

    public A_tenant() {
    }

//    public A_tenant(String firstName, String lastName, String email, String phone) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.phone = phone;
//    }


    public A_tenant(@NonNull String firstName, @NonNull String lastName, @NonNull String email, String phone, List<A_flat> flats, Users user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.flats = flats;
        this.user = user;
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
