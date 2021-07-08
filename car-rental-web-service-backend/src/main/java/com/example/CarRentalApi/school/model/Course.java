package com.example.CarRentalApi.school.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
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
    private Long teacherid;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "creditid")
    private Set<Credit> credits;
    {
        this.credits = new HashSet<>();
    }
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "categoryid")
    private Category category;

    public Course() {
    }

    public Course(String name, Long teacherid) {
        this.name = name;
        this.teacherid = teacherid;
    }

    public Course(Long id, String name, Long teacherid) {
        this.id = id;
        this.name = name;
        this.teacherid = teacherid;

    }

    public Set<Credit> getCredits() {
        return credits;
    }

    public void setCredits(Set<Credit> credits) {
        this.credits = credits;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        return teacherid;
    }

    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }
}


