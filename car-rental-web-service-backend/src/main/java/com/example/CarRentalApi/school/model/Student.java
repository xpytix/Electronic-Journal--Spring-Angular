package com.example.CarRentalApi.school.model;

import com.example.CarRentalApi.school.model.dto.StudentDto;
import com.example.CarRentalApi.school.model.dto.TeacherDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "Student")
@Data
public class Student {

    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "school_sequence")
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    @OneToMany(mappedBy="student",cascade={CascadeType.ALL},orphanRemoval=true)
    List<Credit> credits = new ArrayList<>();

    public void addCredit(Credit credit) {
        credits.add(credit);
        credit.setStudent(this);
    }

    public Student(String email, String firstName, String lastName, String dateOfBirth) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Student(Long id, String email, String firstName, String lastName, String dateOfBirth) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Student() {
    }

    public StudentDto mapStudentWithCredits() {
        return StudentDto
                .builder()
                .email(getEmail())
                .firstName(getFirstName())
                .lastName(getLastName())
                .dateOfBirth(getLastName())
                .credits(getCredits().stream().map(credit -> credit.mapCreditToDto()).collect(Collectors.toList()))
                .build();
    }

}
