package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.database.entity.enums.LessonType;

import javax.persistence.*;

@Entity
@Table(name = "lessons", schema = "ntu")
public class Lesson {

    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lessonId;

    @Column (name="lesson_name")
    private String lessonName;

    @Column(name = "lesson_type")
    @Enumerated(EnumType.ORDINAL)
    private LessonType lessonType;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Subject.class)
    @JoinColumn(name = "subject", nullable = false)
    private Subject subject;

    public Lesson(){}

    public Lesson(String lessonName, LessonType lessonType, Subject subject) {
        this.lessonName = lessonName;
        this.lessonType = lessonType;
        this.subject = subject;
    }

    public Long getLessonId() {
        return lessonId;
    }
    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }
    public String getLessonName() {
        return lessonName;
    }
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
    public LessonType getLessonType() {
        return lessonType;
    }
    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lesson{");
        sb.append("lessonId=").append(lessonId);
        sb.append(", lessonName='").append(lessonName).append('\'');
        sb.append(", lessonType=").append(lessonType);
        sb.append(", subject=").append(subject);
        sb.append('}');
        return sb.toString();
    }
}
