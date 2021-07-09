package com.example.CarRentalApi.school.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "school_sequence",
            sequenceName = "school_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "school_sequence"
    )
    private Long id;
    private String email;
    private String FirstName;
    private String LastName;
    private String DateOfBirth;
    @OneToMany
    @JoinColumn(name= "teacher_id")
    private Set<Course> courses = new HashSet<>();



    public Teacher() {
    }

    public Teacher(Long id, String email, String firstName, String lastName, String dateOfBirth) {
        this.id = id;
        this.email = email;
        FirstName = firstName;
        LastName = lastName;
        DateOfBirth = dateOfBirth;
    }

    public Teacher(String email, String firstName, String lastName, String dateOfBirth) {
        this.email = email;
        FirstName = firstName;
        LastName = lastName;
        DateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }
}