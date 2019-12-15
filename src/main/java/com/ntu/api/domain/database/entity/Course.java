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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", targetEntity = Semester.class)
    private List<Semester> semesters = new ArrayList<>();

    public Course(){}

    public Course(String courseName, Curriculum curriculum) {
        this.courseName = courseName;
        this.curriculum = curriculum;
    }

    public Course(String courseName, Curriculum curriculum, List<Group> groups, List<Semester> semesters) {
        this.courseName = courseName;
        this.curriculum = curriculum;
        this.groups = groups;
        this.semesters = semesters;
    }

    public Long getCourseId() {
        return courseId;
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
    public List<Semester> getSemesters() {
        return semesters;
    }
    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    private String groupsToString(){
        StringBuilder sb = new StringBuilder();
        for(Group group: groups){
            sb.append(group.getGroupName() + "/n");
        }
        return sb.toString();
    }

    private String semestersToString(){
        StringBuilder sb = new StringBuilder();
        for(Semester semester: semesters){
            sb.append(semester.getSemesterName() + "/n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("courseId=").append(courseId);
        sb.append(", courseName='").append(courseName).append('\'');
        sb.append(", curriculum=").append(curriculum.getCurriculumName());
        sb.append(", groups=").append(groupsToString());
        sb.append(", semesters=").append(semestersToString());
        sb.append('}');
        return sb.toString();
    }
}
