package com.example.CarRentalApi.school.model;

import com.example.CarRentalApi.school.model.dto.CategoryDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "Category")
@Data
public class Category {

    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "school_sequence")
    private Long id;
    private String name;

    @OneToMany(mappedBy="category",cascade={CascadeType.ALL},orphanRemoval=true)
    List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
        course.setCategory(this);
    }
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public CategoryDto mapCourseToDtoWithoutCategory() {
        return CategoryDto
                .builder()
                .name(getName())
                .courses(getCourses().stream().map(course -> course.mapCourseToDtoWithTeacher()).collect(Collectors.toList()))
                .build();

    }
}
