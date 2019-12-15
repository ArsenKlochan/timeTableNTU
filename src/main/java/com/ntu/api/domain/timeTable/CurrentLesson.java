package com.ntu.api.domain.timeTable;

import com.ntu.api.domain.database.entity.ClassRoom;
import com.ntu.api.domain.database.entity.Lesson;
import com.ntu.api.domain.database.entity.Teacher;

public class CurrentLesson {
    private Lesson lesson;
    private Teacher teacher;
    private ClassRoom classRoom;

    public Lesson getLesson() {
        return lesson;
    }
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public ClassRoom getClassRoom() {
        return classRoom;
    }
    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public String getParametersToView(){
        return lesson.getLessonName() + " " + teacher.getTeacherPosition() + " " + teacher.getTeacherSurname() + " " + teacher.getTeacherName() + " " + classRoom.getClassRoomName();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(lesson.getLessonName()).append(" ");
        sb.append(lesson.getLessonType()).append(" ");
        sb.append(teacher.getTeacherPosition()).append(" ");
        sb.append(teacher.getTeacherSurname()).append(" ").append(teacher.getTeacherName());
        sb.append(classRoom.getClassRoomName());
        return sb.toString();
    }
}
