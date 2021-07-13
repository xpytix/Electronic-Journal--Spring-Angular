package com.example.CarRentalApi.school.model;

import com.example.CarRentalApi.school.model.dto.CourseDto;
import lombok.Data;


import javax.persistence.*;


@Entity
@Table(name = "Course")
@Data
public class Course {
    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "school_sequence")
    private Long id;
    private String name;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

     @OneToOne(mappedBy = "course")
       private Credit credit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    // MAP TEACHER TO DTO
    public CourseDto mapCourseToDtoWithoutTeacher() {
        return CourseDto
                .builder()
                .name(getName())
                .build();
    }

    public CourseDto mapCourseToDtoWithTeacher() {
        return CourseDto
                .builder()
                .name(getName())
                .teacher(getTeacher().mapTeacherToDtoWithoutCourse())
                .build();
    }

    public CourseDto mapCourseToDtoWithOutCredit() {
        return CourseDto
                .builder()
                .name(getName())
                .build();
    }
    public CourseDto mapCourseToDtoWithoutCategory() {
        return CourseDto
                .builder()
                .name(getName())
                .build();
    }


}
