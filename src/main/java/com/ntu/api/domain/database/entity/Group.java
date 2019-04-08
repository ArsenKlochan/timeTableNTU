package com.ntu.api.domain.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "groups", schema = "ntu")
public class Group {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_description")
    private Integer studentsNumber;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Group(){}

    public Group(String groupName, Integer studentsNumber, Course course) {
        this.groupName = groupName;
        this.studentsNumber = studentsNumber;
        this.course = course;
    }

    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Group{");
        sb.append("groupId=").append(groupId);
        sb.append(", groupName='").append(groupName).append('\'');
        sb.append(", studentsNumber=").append(studentsNumber);
        sb.append(", course=").append(course);
        sb.append('}');
        return sb.toString();
    }
}
