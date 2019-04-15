package com.ntu.api.domain.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "faculties", schema = "ntu")
public class Faculty  {

    @Id
    @Column(name = "faculty_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long facultyId;

    @Column(name= "faculty_name")
    private String facultyName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty", targetEntity = Department.class)
    private List<Department> departments = new ArrayList<>();

    public Faculty(){}

    public Faculty(String facultyName) {
        this.facultyName = facultyName;
    }

    public Faculty(String facultyName, List<Department> departments) {
        this.facultyName = facultyName;
        this.departments = departments;
    }

    public Long getFacultyId() {
        return facultyId;
    }
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }
    public String getFacultyName() {
        return facultyName;
    }
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
    public List<Department> getDepartments() {
        return departments;
    }
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Faculty{");
        sb.append("facultyId=").append(facultyId);
        sb.append(", facultyName='").append(facultyName).append('\'');
        sb.append(", departments=").append(departments);
        sb.append('}');
        return sb.toString();
    }
}
