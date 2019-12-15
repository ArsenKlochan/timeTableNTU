package com.ntu.api.domain.timeTable;

import com.ntu.api.domain.database.entity.Lesson;

public class Day {
    private CurrentLesson [] lessonsList;

    public Day(){
        CurrentLesson [] lessonsList = new CurrentLesson[8];
        lessonsList[0] = new CurrentLesson();
        lessonsList[1] = new CurrentLesson();
        lessonsList[2] = new CurrentLesson();
        lessonsList[3] = new CurrentLesson();
        lessonsList[4] = new CurrentLesson();
        lessonsList[5] = new CurrentLesson();
        lessonsList[6] = new CurrentLesson();
        lessonsList[7] = new CurrentLesson();
    }

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
