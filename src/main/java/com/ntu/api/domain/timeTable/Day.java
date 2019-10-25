package com.ntu.api.domain.timeTable;

import com.ntu.api.domain.database.entity.Lesson;

public class Day {
    private CurrentLesson [] lessonsList = new CurrentLesson[8];

    public CurrentLesson[] getLessonsList() {
        return lessonsList;
    }
    public void setLessonsList(CurrentLesson[] lessonsList) {
        this.lessonsList = lessonsList;
    }

    public void setLesson(int position, CurrentLesson lesson){
        lessonsList[position] = lesson;
    }

    public void removeLesson(int position){
        lessonsList[position] = null;
    }
}
