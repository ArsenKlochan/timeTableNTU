package com.ntu.api.domain.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "faculties", schema = "ntu")
public class Faculty {

    @Id
    @Column(name = "faculty_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long facultyId;

    @Column(name= "faculty_name")
    private String facultyName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Building.class)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty", targetEntity = Speciality.class)
    private List<Speciality> specialities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty", targetEntity = Department.class)
    private List<Department> departments = new ArrayList<>();

    public Faculty(){}

    public Faculty(String facultyName, Building building) {
        this.facultyName = facultyName;
        this.building = building;
    }

    public Faculty(String facultyName, Building building, List<Speciality> specialities, List<Department> departments) {
        this.facultyName = facultyName;
        this.building = building;
        this.specialities = specialities;
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
    public Building getBuilding() {
        return building;
    }
    public void setBuilding(Building building) {
        this.building = building;
    }
    public List<Speciality> getSpecialities() {
        return specialities;
    }
    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
    public List<Department> getDepartments() {
        return departments;
    }
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    private String specialitiesToString(){
        StringBuilder sb = new StringBuilder();
        for (Speciality speciality : specialities) {
            sb.append(speciality.getSpecialityCode()+ " " + speciality.getSpecialityName() + "/n");
        }
        return sb.toString();
    }

    private String departmentsToString(){
        StringBuilder sb = new StringBuilder();
        for (Department dep: departments) {
            sb.append(dep.getDepartmentCode() + " " + dep.getDepartmentName() + "/n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Faculty{");
        sb.append("facultyId=").append(facultyId);
        sb.append(", facultyName='").append(facultyName).append('\'');
        sb.append(", building=").append(building);
        sb.append(", specialities=").append(specialitiesToString());
        sb.append(", departments=").append(departmentsToString());
        sb.append('}');
        return sb.toString();
    }
}
