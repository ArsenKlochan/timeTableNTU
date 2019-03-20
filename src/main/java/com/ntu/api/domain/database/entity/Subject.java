package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.database.entity.enums.ExamType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects", schema = "ntu")
public class Subject {

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subjectId;

    @Column (name="subject_name")
    private String subjectName;

    @Column(name = "lection")
    private Integer lection;

    @Column(name = "practic")
    private Integer practic;

    @Column(name = "labaratory")
    private Integer labaaratory;

    @Column(name = "all_hours")
    private Integer allHours;

    @Column(name = "exam _type")
    @Enumerated(EnumType.ORDINAL)
    private ExamType examType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Speciality.class)
    @JoinTable(name = "subject_on_specialities",
            joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id", referencedColumnName = "speciality_id"))
    private List<Speciality> specialities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Course.class)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", targetEntity = Lesson.class)
    private List<Lesson> lessons = new ArrayList<>();

    private Subject(){}

    public Subject(String subjectName, Course course, Integer lection, Integer practic,
                   Integer labaaratory, Integer allHours, ExamType examType) {
        this.subjectName = subjectName;
        this.course = course;
        this.lection = lection;
        this.practic = practic;
        this.labaaratory = labaaratory;
        this.allHours = allHours;
        this.examType = examType;
    }

    public Subject(String subjectName, List<Speciality> specialities, Course course,
                   Integer lection, Integer practic, Integer labaaratory, Integer allHours, ExamType examType, List<Lesson> lessons) {
        this.subjectName = subjectName;
        this.specialities = specialities;
        this.course = course;
        this.lection = lection;
        this.practic = practic;
        this.labaaratory = labaaratory;
        this.allHours = allHours;
        this.examType = examType;
        this.lessons = lessons;
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
    public List<Speciality> getSpecialities() {
        return specialities;
    }
    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
    public Integer getLection() {
        return lection;
    }
    public void setLection(Integer lection) {
        this.lection = lection;
    }
    public Integer getPractic() {
        return practic;
    }
    public void setPractic(Integer practic) {
        this.practic = practic;
    }
    public Integer getLabaaratory() {
        return labaaratory;
    }
    public void setLabaaratory(Integer labaaratory) {
        this.labaaratory = labaaratory;
    }
    public Integer getAllHours() {
        return allHours;
    }
    public void setAllHours(Integer allHours) {
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

    private String specialityToString(){
        StringBuilder sb = new StringBuilder();
        for(Speciality speciality: specialities){
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
        final StringBuilder sb = new StringBuilder("Subject{");
        sb.append("subjectId=").append(subjectId);
        sb.append(", subjectName='").append(subjectName).append('\'');
        sb.append(", specialities=").append(specialityToString());
        sb.append(", course=").append(course);
        sb.append(", lection=").append(lection);
        sb.append(", practic=").append(practic);
        sb.append(", labaaratory=").append(labaaratory);
        sb.append(", allHours=").append(allHours);
        sb.append(", examType=").append(examType);
        sb.append(", lessons=").append(lessonsToString());
        sb.append('}');
        return sb.toString();
    }
}
