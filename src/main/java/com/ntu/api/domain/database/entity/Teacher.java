package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.BaseObject;
import com.ntu.api.domain.ObjectWithTimeTable;
import com.ntu.api.domain.timeTable.TimeTableObject;

import javax.persistence.*;

@Entity
@Table(name = "teachers", schema = "ntu")
public class Teacher extends BaseObject implements ObjectWithTimeTable {
    @Id
    @Column(name = "teacher_id")
    @SequenceGenerator(name = "teacherId", sequenceName = "seq_teacher_id", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacherId")
    private Long teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_surname")
    private String teacherSurname;

    @Column(name = "teacher_position")
    private String teacherPosition;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Transient
    private TimeTableObject timeTable;

    public Teacher() {
    }

    public Teacher(String teacherSurname, String teacherName, Department department, String teacherPosition, TimeTableObject timeTable) {
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.teacherPosition = teacherPosition;
        this.department = department;
        this.timeTable = timeTable;
    }
    public Teacher(String teacherSurname, String teacherName, Department department, String teacherPosition) {
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.teacherPosition = teacherPosition;
        this.department = department;
    }

    public Long getTeacherId() {
        return teacherId;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getTeacherSurname() {
        return teacherSurname;
    }
    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }
    public String getTeacherPosition() {
        return teacherPosition;
    }
    public void setTeacherPosition(String teacherPosition) {
        this.teacherPosition = teacherPosition;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public TimeTableObject getTimeTable() {
        return timeTable;
    }
    public void setTimeTable(TimeTableObject timeTable) {
        this.timeTable = timeTable;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Teacher{");
        sb.append("teacherId=").append(teacherId);
        sb.append(", teacherName='").append(teacherName).append('\'');
        sb.append(", teacherSurname='").append(teacherSurname).append('\'');
        sb.append(", teacherPosition='").append(teacherPosition).append('\'');
        sb.append(", department=").append(department.getDepartmentName());
        sb.append('}');
        return sb.toString();
    }
}
