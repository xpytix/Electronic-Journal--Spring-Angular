package com.example.CarRentalApi.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Credit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit implements Serializable {

    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "school_sequence")
    private Long id;
    private Integer grade;
    private Boolean attempt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="student_id")
    private Student student;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;


//    public Credit() {
//    }
//
    public Credit(Long id, Integer grade, Boolean attempt) {
        this.id = id;
        this.grade = grade;
        this.attempt = attempt;

    }
    public Credit(Integer grade, Boolean attempt) {
        this.grade = grade;
        this.attempt = attempt;

    }

}
