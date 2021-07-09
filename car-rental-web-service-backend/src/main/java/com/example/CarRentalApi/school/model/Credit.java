package com.example.CarRentalApi.school.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Credit")
public class Credit {

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
    private Integer grade;
    private Boolean attempt;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_course")
    private Course courseCredit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "credit")
    private Set<Student> students = new HashSet<>();


    public Credit() {
    }

    public Credit(Long id, Integer grade, Boolean attempt) {
        this.id = id;
        this.grade = grade;
        this.attempt = attempt;

    }

    public Credit(Integer grade, Boolean attempt) {
        this.grade = grade;
        this.attempt = attempt;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        grade = grade;
    }

    public Boolean getAttempt() {
        return attempt;
    }

    public void setAttempt(Boolean attempt) {
        attempt = attempt;
    }
}