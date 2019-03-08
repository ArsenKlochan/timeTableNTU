package com.ntu.api.domain.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "speciality")
public class Speciality {

    @Id
    @Column(name = "spesiality_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long specialityId;

    @Column(name = "spesiality_name")
    private String specialityName;

    @Column(name = "speciality_description")
    private String specialityDescription;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Curriculum.class)
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality", targetEntity = Course.class)
    private List<Course> courses = new ArrayList<>();

    public Speciality(){}

    public Speciality(String specialityName, String specialityDescription, Curriculum curriculum,
                      Department department) {
        this.specialityName = specialityName;
        this.specialityDescription = specialityDescription;
        this.curriculum = curriculum;
        this.department = department;
    }

    public Speciality(String specialityName, String specialityDescription, Curriculum curriculum,
                      Department department, List<Course> courses) {
        this.specialityName = specialityName;
        this.specialityDescription = specialityDescription;
        this.curriculum = curriculum;
        this.department = department;
        this.courses = courses;
    }

    public Long getSpecialityId() {
        return specialityId;
    }
    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }
    public String getSpecialityName() {
        return specialityName;
    }
    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }
    public String getSpecialityDescription() {
        return specialityDescription;
    }
    public void setSpecialityDescription(String specialityDescription) {
        this.specialityDescription = specialityDescription;
    }
    public Curriculum getCurriculum() {
        return curriculum;
    }
    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    private String courseToString(){
        StringBuilder sb = new StringBuilder();
        for(Course course: courses){
            sb.append(course.getCourseName() + "/n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Speciality{");
        sb.append("specialityId=").append(specialityId);
        sb.append(", specialityName='").append(specialityName).append('\'');
        sb.append(", specialityDescription='").append(specialityDescription).append('\'');
        sb.append(", curriculum=").append(curriculum.getCurriculumName());
        sb.append(", department=").append(department.getDepartmentName());
        sb.append(", courses=").append(courseToString());
        sb.append('}');
        return sb.toString();
    }
}
