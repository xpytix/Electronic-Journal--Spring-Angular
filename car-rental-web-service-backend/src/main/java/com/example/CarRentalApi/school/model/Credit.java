package com.example.CarRentalApi.school.model;

import com.example.CarRentalApi.school.model.dto.CreditDto;
import lombok.Data;


import javax.persistence.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "Credit")
@Data
public class Credit {

    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "school_sequence")
    private Long id;
    private Integer grade;
    private Boolean attempt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="student_id")
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;


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
    public CreditDto mapCreditToDto() {
        return CreditDto
                .builder()
                .grade(getGrade())
                .attempt(getAttempt())
                .build();
    }
    public CreditDto mapCreditToDtoWithCourse() {
        return CreditDto
                .builder()
                .grade(getGrade())
                .attempt(getAttempt())
                .course(getCourse().mapCourseToDtoWithOutCredit())
                .build();
    }
}
