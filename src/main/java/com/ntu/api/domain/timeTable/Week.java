package com.ntu.api.domain.timeTable;

public class Week {
    private  Day[] dayList = new Day[7];

    public Day[] getDayList() {
        return dayList;
    }
    public void setDayList(Day[] dayList) {
        this.dayList = dayList;
    }

    public void setDay(int position, Day day){
        dayList[position] = day;
    }
}
