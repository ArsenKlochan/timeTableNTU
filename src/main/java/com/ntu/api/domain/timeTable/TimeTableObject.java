package com.ntu.api.domain.timeTable;

public class TimeTableObject {
    private String name;
    private Week[] weeks;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public TimeTableObject(){
        Week[] weeks = new Week[2];
        weeks[0] = new Week();
        weeks[1] = new Week();
    }

    public Week[] getWeeks() {
        return weeks;
    }
    public void setWeeks(Week[] weeks) {
        this.weeks = weeks;
    }

    public void setWeek(int position, Week week){weeks[position] = week;}
}
