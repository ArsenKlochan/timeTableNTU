package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.BaseObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "semesters", schema = "ntu")
public class Semester extends BaseObject {

    @Id
    @Column(name = "semester_id")
    @SequenceGenerator(name = "semesterId", sequenceName = "seq_semester_id", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "semesterId")
    private Long semesterId;

    @Column(name = "semester_name")
    private String semesterName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "semester", targetEntity = Subjects.class)
    private List<Subjects> subjects = new ArrayList<>();

    public Semester(){}

    public Semester(String semesterName, Course course){
        this.semesterName = semesterName;
        this.course = course;
    }

    public Semester(String semesterName, Course course, List<Subjects> subjects) {
        this.semesterName = semesterName;
        this.course = course;
        this.subjects = subjects;
    }

    public Long getSemesterId() {
        return semesterId;
    }
    public String getSemesterName() {
        return semesterName;
    }
    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public List<Subjects> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
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
        final StringBuilder sb = new StringBuilder("Semester{");
        sb.append("semesterId=").append(semesterId);
        sb.append(", semesterName='").append(semesterName).append('\'');
        sb.append(", course=").append(course.getCourseName());
        sb.append(", subjects=").append(subjectsToString());
        sb.append('}');
        return sb.toString();
    }
}
