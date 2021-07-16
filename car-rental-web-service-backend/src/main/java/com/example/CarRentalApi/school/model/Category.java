package com.example.CarRentalApi.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

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
//    public Category() {
//    }

    public Category(String name) {
        this.name = name;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
