package com.example.CarRentalApi.school.model;

import com.example.CarRentalApi.school.dto.TeacherDto;
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

    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "school_sequence")
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    @OneToMany(mappedBy="teacher",cascade={CascadeType.ALL},orphanRemoval=true)
    List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
        course.setTeacher(this);
    }
//    public Teacher() {
//    }
//
//    public Teacher(Long id, String email, String firstName, String lastName, String dateOfBirth) {
//        this.id = id;
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//    }
//
    public Teacher(String email, String firstName, String lastName, String dateOfBirth) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    public Teacher(TeacherDto teacherDto)
    {
        this.email = teacherDto.getEmail();
        this.firstName = teacherDto.getFirstName();
        this.lastName = teacherDto.getLastName();
        this.dateOfBirth = teacherDto.getDateOfBirth();
    }

}
