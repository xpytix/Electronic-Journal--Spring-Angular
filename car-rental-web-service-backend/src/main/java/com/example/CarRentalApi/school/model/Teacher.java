package com.example.CarRentalApi.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Teacher")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher implements Serializable {

    @OneToMany(mappedBy = "teacher", cascade = { CascadeType.MERGE }, orphanRemoval = true)
    List<Course> courses = new ArrayList<>();
    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "school_sequence")
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Teacher(String email, String firstName, String lastName, String dateOfBirth) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.setTeacher(this);
    }

}
