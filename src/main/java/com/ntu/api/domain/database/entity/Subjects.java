package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.BaseObject;
import com.ntu.api.domain.database.entity.enums.ExamType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects", schema = "ntu")
public class Subjects extends BaseObject {

    @Id
    @Column(name = "subject_id")
    @SequenceGenerator(name = "subjectId", sequenceName = "seq_subject_id", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subjectId")
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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Semester.class)
    @JoinColumn(name = "semester_id",nullable = false)
    private Semester semester;

    public Subjects(){}

    public Subjects(String subjectName, int lection, int practic, int labaratory, int allHours, ExamType examType) {
        this.subjectName = subjectName;
        this.lection = lection;
        this.practic = practic;
        this.labaratory = labaratory;
        this.allHours = allHours;
        this.examType = examType;
    }

    public Subjects(String subjectName, int lection, int practic, int labaratory, int allHours, ExamType examType, Semester semester) {
        this.subjectName = subjectName;
        this.lection = lection;
        this.practic = practic;
        this.labaratory = labaratory;
        this.allHours = allHours;
        this.examType = examType;
        this.semester = semester;
    }

    public Subjects(String subjectName, int lection, int practic, int labaratory, int allHours, ExamType examType,
                    List<Lesson> lessons, Semester semester) {
        this.subjectName = subjectName;
        this.lection = lection;
        this.practic = practic;
        this.labaratory = labaratory;
        this.allHours = allHours;
        this.examType = examType;
        this.lessons = lessons;
        this.semester = semester;
    }

    public Long getSubjectId() {
        return subjectId;
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
    public Semester getSemester() {
        return semester;
    }
    public void setSemester(Semester semester) {
        this.semester = semester;
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
        sb.append(", semester=").append(semester.getSemesterName());
        sb.append('}');
        return sb.toString();
    }
}
