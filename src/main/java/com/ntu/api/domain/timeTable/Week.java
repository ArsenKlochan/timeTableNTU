package com.ntu.api.domain.timeTable;

public class Week {
    private  Day[] dayList;

    public Week(){
        Day[] dayList = new Day[7];
        dayList[0] = new Day();
        dayList[1] = new Day();
        dayList[2] = new Day();
        dayList[3] = new Day();
        dayList[4] = new Day();
        dayList[5] = new Day();
        dayList[6] = new Day();
    }

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
