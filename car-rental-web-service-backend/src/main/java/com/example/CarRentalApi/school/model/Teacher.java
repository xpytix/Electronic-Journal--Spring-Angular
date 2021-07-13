package com.example.CarRentalApi.school.model;

import com.example.CarRentalApi.school.model.dto.TeacherDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "Teacher")
@Data
public class Teacher {

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
    public Teacher() {
    }

    public Teacher(Long id, String email, String firstName, String lastName, String dateOfBirth) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

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
    public TeacherDto mapTeacherToDtoWithCourse() {
        return TeacherDto
                .builder()
                .firstName(getFirstName())
                .lastName(getLastName())
                .dateOfBirth(getLastName())
                .courses(getCourses().stream().map(course -> course.mapCourseToDtoWithoutTeacher()).collect(Collectors.toList()))
                .build();
    }
    public TeacherDto mapTeacherToDtoWithoutCourse() {
        return TeacherDto
                .builder()
                .firstName(getFirstName())
                .lastName(getLastName())
                .dateOfBirth(getLastName())
                .build();
    }
}
