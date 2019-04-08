package com.ntu.api.domain.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments", schema = "ntu")
public class Department {

    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @Column(name = "department_code")
    private String departmentCode;

    @Column (name="department_name")
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Faculty.class)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", targetEntity = Speciality.class)
    private List<Speciality> specialities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", targetEntity = Teacher.class)
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", targetEntity = ClassRoom.class)
    private List<ClassRoom> classRooms = new ArrayList<>();

    public Department(){}

    public Department(String departmentCode, String departmentName, Faculty faculty) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.faculty = faculty;
    }

    public Department(String departmentCode, String departmentName, Faculty faculty,
                      List<Speciality> specialities, List<Teacher> teachers, List<ClassRoom> classRooms) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.faculty = faculty;
        this.specialities = specialities;
        this.teachers = teachers;
        this.classRooms = classRooms;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentCode() {
        return departmentCode;
    }
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Faculty getFaculty() {
        return faculty;
    }
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public List<Speciality> getSpecialities() {
        return specialities;
    }
    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
    public List<Teacher> getTeachers() {
        return teachers;
    }
    public void setTeachetrs(List<Teacher> teachetrs) {
        this.teachers = teachetrs;
    }
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
    public List<ClassRoom> getClassRooms() {
        return classRooms;
    }
    public void setClassRooms(List<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }

    private String specialitiesToString(){
        StringBuilder sb = new StringBuilder();
        for(Speciality speciality : specialities){
            sb.append(speciality.getSpecialityId() + " " + speciality.getSpecialityName() + "/n");
        }
        return sb.toString();
    }

    private String teacherToString(){
        StringBuilder sb = new StringBuilder();
        for(Teacher teacher: teachers ){
            sb.append(teacher.getTeacherSurname() + " " + teacher.getTeacherName() + "/n");
        }
        return sb.toString();
    }

    private String classRoomsToString(){
        StringBuilder sb = new StringBuilder();
        for(ClassRoom classRoom: classRooms){
            sb.append(classRoom.getClassRoomName() + " " + classRoom.getType() + "/n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Department{");
        sb.append("departmentId=").append(departmentId);
        sb.append(", departmentCode='").append(departmentCode).append('\'');
        sb.append(", departmentName='").append(departmentName).append('\'');
        sb.append(", faculty=").append(faculty);
        sb.append(", specialities=").append(specialitiesToString());
        sb.append(", teachers=").append(teacherToString());
        sb.append(", classRooms=").append(classRoomsToString());
        sb.append('}');
        return sb.toString();
    }
}

