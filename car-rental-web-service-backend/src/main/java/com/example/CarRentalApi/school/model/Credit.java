package com.example.CarRentalApi.school.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
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
    private Long studentid;
    private Long courseid;

    public Credit() {
    }

    public Credit(Long id, Integer grade, Boolean attempt, Long studentId, Long courseid) {
        this.id = id;
        Grade = grade;
        Attempt = attempt;
        this.studentid = studentId;
        this.courseid = courseid;
    }

    public Credit(Integer grade, Boolean attempt, Long studentId, Long courseid) {
        Grade = grade;
        Attempt = attempt;
        this.studentid = studentId;
        this.courseid = courseid;
    }

    public Long getStudentid() {
        return studentid;
    }

    public void setStudentid(Long studentid) {
        this.studentid = studentid;
    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public Long getStudentId() {
        return studentid;
    }

    public void setStudentId(Long studentId) {
        this.studentid = studentId;
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