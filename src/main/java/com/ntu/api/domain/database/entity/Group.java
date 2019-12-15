package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.BaseObject;
import com.ntu.api.domain.ObjectWithTimeTable;
import com.ntu.api.domain.timeTable.TimeTableObject;

import javax.persistence.*;

@Entity
@Table(name = "groups", schema = "ntu")
public class Group extends BaseObject implements ObjectWithTimeTable {

    @Id
    @Column(name = "group_id")
    @SequenceGenerator(name = "groupId", sequenceName = "seq_group_id", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupId")
    private Long groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_description")
    private Integer studentsNumber;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Transient
    private TimeTableObject timeTable;

    public Group(String groupName, Integer studentsNumber, Course course, TimeTableObject timeTable) {
        this.groupName = groupName;
        this.studentsNumber = studentsNumber;
        this.course = course;
        this.timeTable = timeTable;
    }

    public Group(){}

    public Group(String groupName, Integer studentsNumber, Course course) {
        this.groupName = groupName;
        this.studentsNumber = studentsNumber;
        this.course = course;
    }

    public Long getGroupId() {
        return groupId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public Integer getStudentsNumber() {
        return studentsNumber;
    }
    public void setStudentsNumber(Integer studentsNumber) {
        this.studentsNumber = studentsNumber;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public TimeTableObject getTimeTable() {
        return timeTable;
    }
    public void setTimeTable(TimeTableObject timeTable) {
        this.timeTable = timeTable;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Group{");
        sb.append("groupId=").append(groupId);
        sb.append(", groupName='").append(groupName).append('\'');
        sb.append(", studentsNumber=").append(studentsNumber);
        sb.append(", course=").append(course.getCourseName());
        sb.append('}');
        return sb.toString();
    }
}
