package com.ntu.api.domain.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curriculums", schema = "ntu")
public class Curriculum {

    @Id
    @Column(name = "curriculum_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long curriculumId;

    @Column(name = "curriculum_name")
    private String curriculumName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Speciality.class)
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curriculum", targetEntity = Course.class)
    private List<Course> courses = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Subjects.class)
    @JoinTable(name = "subject_on_curriculums",
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "subject_id"),
            joinColumns = @JoinColumn(name = "curriculum_id", referencedColumnName = "curriculum_id"))
    private List<Subjects> subjects = new ArrayList<>();

    public Curriculum(){}

    public Curriculum(String curriculumName, Speciality speciality, Department department) {
        this.curriculumName = curriculumName;
        this.speciality = speciality;
        this.department = department;
    }

    public Curriculum(String curriculumName, Speciality speciality, Department department, List<Course> courses, List<Subjects> subjects) {
        this.curriculumName = curriculumName;
        this.speciality = speciality;
        this.department = department;
        this.courses = courses;
        this.subjects = subjects;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }
    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }
    public String getCurriculumName() {
        return curriculumName;
    }
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }
    public Speciality getSpeciality() {
        return speciality;
    }
    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
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
        final StringBuilder sb = new StringBuilder("Curriculum{");
        sb.append("specialityId=").append(curriculumId);
        sb.append(", specialityName='").append(curriculumName).append('\'');
        sb.append(", speciality=").append(speciality);
        sb.append(", department=").append(department);
        sb.append(", courses=").append(courseToString());
        sb.append(", subjects=").append(subjectToString());
        sb.append('}');
        return sb.toString();
    }
}
