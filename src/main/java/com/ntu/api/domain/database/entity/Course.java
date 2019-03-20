package com.ntu.api.domain.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course", schema = "ntu")
public class Course {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;

    @Column(name = "course_name")
    private String courseName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Speciality.class)
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course", targetEntity = Group.class)
    private List<Group> groups = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", targetEntity = Subject.class)
    private List<Subject> subjects = new ArrayList<>();

    public Course(){}

    public Course(String courseName, Speciality speciality) {
        this.courseName = courseName;
        this.speciality = speciality;
    }

    public Course(String courseName, Speciality speciality, List<Group> groups, List<Subject> subjects) {
        this.courseName = courseName;
        this.speciality = speciality;
        this.groups = groups;
        this.subjects = subjects;
    }

    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public Speciality getSpeciality() {
        return speciality;
    }
    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    public List<Subject> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    private String groupsToString(){
        StringBuilder sb = new StringBuilder();
        for(Group group: groups){
            sb.append(group.getGroupName() + "/n");
        }
        return sb.toString();
    }

    private String subjectsToString(){
        StringBuilder sb = new StringBuilder();
        for(Subject subject: subjects){
            sb.append(subject.getSubjectName() + " " + subject.getAllHours() + "/n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("courseId=").append(courseId);
        sb.append(", courseName='").append(courseName).append('\'');
        sb.append(", speciality=").append(speciality);
        sb.append(", groups=").append(groupsToString());
        sb.append(", subjects=").append(subjectsToString());
        sb.append('}');
        return sb.toString();
    }
}
