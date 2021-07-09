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
    private Integer Grade;
    private Boolean Attempt;
    private Long student_id;
    private Long course_id;

    public Credit() {
    }

    public Credit(Long id, Integer grade, Boolean attempt) {
        this.id = id;
        Grade = grade;
        Attempt = attempt;

    }

    public Credit(Integer grade, Boolean attempt) {
        Grade = grade;
        Attempt = attempt;

    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return Grade;
    }

    public void setGrade(Integer grade) {
        Grade = grade;
    }

    public Boolean getAttempt() {
        return Attempt;
    }

    public void setAttempt(Boolean attempt) {
        Attempt = attempt;
    }
}