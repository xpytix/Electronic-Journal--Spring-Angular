package com.example.CarRentalApi.school.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.example.CarRentalApi.school.dto.TeacherDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private String password;
    private String role;
    private Boolean active;
    private String dateOfBirth;

    public Teacher(String email, String firstName, String lastName, String password, String dateOfBirth, String role, Boolean active) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.setTeacher(this);
    }


}
