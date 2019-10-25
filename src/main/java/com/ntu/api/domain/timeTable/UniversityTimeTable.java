package com.ntu.api.domain.timeTable;

import java.util.ArrayList;

public class UniversityTimeTable {
    private ArrayList<TimeTableObject> timeTables = new ArrayList<>();

    public void add(TimeTableObject timeTable){
        timeTables.add(timeTable);
    }
    public void remove(TimeTableObject timeTable){
        timeTables.remove(timeTable);
    }
    public void remove(int number){
        timeTables.remove(number);
    }
    public TimeTableObject get(int number){
        return timeTables.get(number);
    }
    public void change(int number, TimeTableObject timeTableObject){
        timeTables.set(number, timeTableObject);
    }

}
