package com.example.CarRentalApi.school.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Course")
public class Course {

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
    private String name;
    private Long teacher_id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "credit_id")
    private Set<Credit> credits = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public Set<Credit> getCredits() {
        return credits;
    }

    public void setCredits(Set<Credit> credits) {
        this.credits = credits;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacherid() {
        return teacher_id;
    }

    public void setTeacherid(Long teacherId) {
        this.teacher_id = teacher_id;
    }
}


