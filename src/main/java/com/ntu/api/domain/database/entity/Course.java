package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.BaseObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses", schema = "ntu")
public class Course extends BaseObject {

    @Id
    @Column(name = "course_id")
    @SequenceGenerator(name = "courseId", sequenceName = "seq_course_id", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseId")
    private Long courseId;

    @Column(name = "course_name")
    private String courseName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Curriculum.class)
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", targetEntity = Group.class)
    private List<Group> groups = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", targetEntity = Subjects.class)
    private List<Subjects> subjects = new ArrayList<>();

    public Course(){}

    public Course(String courseName, Curriculum curriculum) {
        this.courseName = courseName;
        this.curriculum = curriculum;
    }

    public Course(String courseName, Curriculum curriculum, List<Group> groups, List<Subjects> subjects) {
        this.courseName = courseName;
        this.curriculum = curriculum;
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
    public Curriculum getCurriculum() {
        return curriculum;
    }
    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    public List<Subjects> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subjects> subjects) {
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
        for(Subjects subject: subjects){
            sb.append(subject.getSubjectName() + " " + subject.getAllHours() + "/n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("courseId=").append(courseId);
        sb.append(", courseName='").append(courseName).append('\'');
        sb.append(", curriculum=").append(curriculum);
        sb.append(", groups=").append(groupsToString());
        sb.append(", subjects=").append(subjectsToString());
        sb.append('}');
        return sb.toString();
    }
}
