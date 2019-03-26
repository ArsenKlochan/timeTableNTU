package com.ntu.api.domain.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "specialities", schema = "ntu")
public class Speciality {

    @Id
    @Column(name = "speciality_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long specialityId;

    @Column(name = "speciality_name")
    private String specialityName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Curriculum.class)
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality", targetEntity = Course.class)
    private List<Course> courses = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Subjects.class)
    @JoinTable(name = "subject_on_specialities",
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "subject_id"),
            joinColumns = @JoinColumn(name = "speciality_id", referencedColumnName = "speciality_id"))
    private List<Subjects> subjects = new ArrayList<>();

    public Speciality(){}

    public Speciality(String specialityName, Curriculum curriculum, Department department) {
        this.specialityName = specialityName;
        this.curriculum = curriculum;
        this.department = department;
    }

    public Speciality(String specialityName, Curriculum curriculum, Department department, List<Course> courses, List<Subjects> subjects) {
        this.specialityName = specialityName;
        this.curriculum = curriculum;
        this.department = department;
        this.courses = courses;
        this.subjects = subjects;
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
    public List<Subjects> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    private String courseToString(){
        StringBuilder sb = new StringBuilder();
        for(Course course: courses){
            sb.append(course.getCourseName() + "/n");
        }
        return sb.toString();
    }

    private String subjectToString(){
        StringBuilder sb = new StringBuilder();
        for(Subjects subject: subjects){
            sb.append(subject.getSubjectName() + "/n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Speciality{");
        sb.append("specialityId=").append(specialityId);
        sb.append(", specialityName='").append(specialityName).append('\'');
        sb.append(", curriculum=").append(curriculum);
        sb.append(", department=").append(department);
        sb.append(", courses=").append(courseToString());
        sb.append(", subjects=").append(subjectToString());
        sb.append('}');
        return sb.toString();
    }
}
