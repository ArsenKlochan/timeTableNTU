package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.enums.ExamType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects", schema = "ntu")
public class Subjects {

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "lection")
    private int lection;

    @Column(name = "practic")
    private int practic;

    @Column(name = "labaratory")
    private int labaratory;

    @Column(name = "all_hours")
    private int allHours;

    @Column(name = "exam_type")
    @Enumerated(EnumType.STRING)
    private ExamType examType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", targetEntity = Lesson.class)
    private List<Lesson> lessons = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class)
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "subjects", cascade = CascadeType.ALL, targetEntity = Speciality.class)
    private List<Speciality> specialities = new ArrayList<>();

    public Subjects(){}

    public Subjects(String subjectName, int lection, int practic, int labaratory, int allHours, ExamType examType) {
        this.subjectName = subjectName;
        this.lection = lection;
        this.practic = practic;
        this.labaratory = labaratory;
        this.allHours = allHours;
        this.examType = examType;
    }

    public Subjects(String subjectName, int lection, int practic, int labaratory, int allHours, ExamType examType,
                    List<Lesson> lessons, Course course, List<Speciality> specialities) {
        this.subjectName = subjectName;
        this.lection = lection;
        this.practic = practic;
        this.labaratory = labaratory;
        this.allHours = allHours;
        this.examType = examType;
        this.lessons = lessons;
        this.course = course;
        this.specialities = specialities;
    }

    public Long getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public int getLection() {
        return lection;
    }
    public void setLection(int lection) {
        this.lection = lection;
    }
    public int getPractic() {
        return practic;
    }
    public void setPractic(int practic) {
        this.practic = practic;
    }
    public int getLabaratory() {
        return labaratory;
    }
    public void setLabaratory(int labaratory) {
        this.labaratory = labaratory;
    }
    public int getAllHours() {
        return allHours;
    }
    public void setAllHours(int allHours) {
        this.allHours = allHours;
    }
    public ExamType getExamType() {
        return examType;
    }
    public void setExamType(ExamType examType) {
        this.examType = examType;
    }
    public List<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public List<Speciality> getSpecialities() {
        return specialities;
    }
    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    private String specialityToString(){
        StringBuilder sb = new StringBuilder();
        for(Speciality speciality : specialities){
            sb.append(speciality.getSpecialityName()+ "/n");
        }
        return sb.toString();
    }

    private String lessonsToString(){
        StringBuilder sb = new StringBuilder();
        for(Lesson lesson: lessons){
            sb.append(lesson.getLessonName() + " " + lesson.getLessonType()+ "/n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subjects{");
        sb.append("subjectId=").append(subjectId);
        sb.append(", subjectName='").append(subjectName).append('\'');
        sb.append(", lection=").append(lection);
        sb.append(", practic=").append(practic);
        sb.append(", labaratory=").append(labaratory);
        sb.append(", allHours=").append(allHours);
        sb.append(", examType=").append(examType);
        sb.append(", lessons=").append(lessonsToString());
        sb.append(", course=").append(course);
        sb.append(", specialities=").append(specialityToString());
        sb.append('}');
        return sb.toString();
    }
}
