package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.BaseObject;
import com.ntu.api.domain.database.entity.enums.LessonType;

import javax.persistence.*;

@Entity
@Table(name = "lessons", schema = "ntu")
public class Lesson extends BaseObject {

    @Id
    @Column(name = "lesson_id")
    @SequenceGenerator(name = "lessonsId", sequenceName = "seq_lessons_id", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessonsId")
    private Long lessonId;

    @Column (name="lesson_name")
    private String lessonName;

    @Column(name = "lesson_type")
    @Enumerated(EnumType.ORDINAL)
    private LessonType lessonType;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Subjects.class)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subjects subject;

    public Lesson(){}

    public Lesson(String lessonName, LessonType lessonType, Subjects subject) {
        this.lessonName = lessonName;
        this.lessonType = lessonType;
        this.subject = subject;
    }

    public Long getLessonId() {
        return lessonId;
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
    public Subjects getSubject() {
        return subject;
    }
    public void setSubject(Subjects subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lesson{");
        sb.append("lessonId=").append(lessonId);
        sb.append(", lessonName='").append(lessonName).append('\'');
        sb.append(", lessonType=").append(lessonType);
        sb.append(", subject=").append(subject.getSubjectName());
        sb.append('}');
        return sb.toString();
    }
}
