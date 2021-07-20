package com.example.CarRentalApi.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student implements Serializable {


    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "school_sequence")
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    @OneToMany(mappedBy = "student", cascade = { CascadeType.ALL }, orphanRemoval = true)
    List<Credit> credits = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Student(String email, String firstName, String lastName, String dateOfBirth) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public void addCredit(Credit credit) {
        credits.add(credit);
        credit.setStudent(this);
    }

}
