package com.example.CarRentalApi.school.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student implements Serializable {

    @OneToMany(mappedBy = "student", cascade = { CascadeType.ALL }, orphanRemoval = true)
    List<Credit> credits = new ArrayList<>();
    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "school_sequence")
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String dateOfBirth;
    private String role;

    public Student(String email, String firstName, String lastName, String dateOfBirth, String password, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.role = role;
    }

    public void addCredit(Credit credit) {
        credits.add(credit);
        credit.setStudent(this);
    }

}
