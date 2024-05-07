package com.springbootproject.object;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_courselist_foreignkey")
    private Teacher teacher;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "course")
    private List<Student> courseStudentListForeignKey;

    public Course(int courseID, String courseName, int courseCapacity) {
        this.id = courseID;
        this.name = courseName;
        this.capacity = courseCapacity;
    }

    @Override
    public String toString() {
        return  name;
    }
}
