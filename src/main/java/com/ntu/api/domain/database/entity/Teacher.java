package com.ntu.api.domain.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher", schema = "ntu")
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_surname")
    private String teacherSurname;

    @Column(name = "teacher_position")
    private String teacherPosition;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Department.class)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public Teacher(){}

    public Teacher(String teacherName, String teacherSurname, String teacherPosition, Department department) {
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.teacherPosition = teacherPosition;
        this.department = department;
    }

    public Long getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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
