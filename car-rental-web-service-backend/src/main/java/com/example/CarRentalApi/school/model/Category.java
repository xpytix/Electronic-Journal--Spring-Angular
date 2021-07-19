package com.example.CarRentalApi.school.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    @OneToMany(mappedBy = "category", cascade = { CascadeType.ALL }, orphanRemoval = true)
    List<Course> courses = new ArrayList<>();
    @Id
    @SequenceGenerator(name = "school_sequence", sequenceName = "school_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "school_sequence")
    private Long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
    // public Category() {
    // }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.setCategory(this);
    }

}
