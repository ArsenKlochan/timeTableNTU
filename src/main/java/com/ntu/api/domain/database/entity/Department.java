package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.BaseObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments", schema = "ntu")
public class Department extends BaseObject {

    @Id
    @Column(name = "department_id")
    @SequenceGenerator(name = "departmentId", sequenceName = "seq_department_id", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departmentId")
    private Long departmentId;

    @Column(name = "department_code")
    private String departmentCode;

    @Column (name="department_name")
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Faculty.class)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", targetEntity = Curriculum.class)
    private List<Curriculum> curriculums = new ArrayList<>();

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
                      List<Curriculum> curriculums, List<Teacher> teachers, List<ClassRoom> classRooms) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.faculty = faculty;
        this.curriculums = curriculums;
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
    public List<Curriculum> getCurriculums() {
        return curriculums;
    }
    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
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

    private String curriculumsToString(){
        StringBuilder sb = new StringBuilder();
        for(Curriculum curriculum : curriculums){
            sb.append(curriculum.getCurriculumName() + "/n");
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
        sb.append(", faculty=").append(getFaculty().getFacultyName());
        sb.append(", curriculums=").append(curriculumsToString());
        sb.append(", teachers=").append(teacherToString());
        sb.append(", classRooms=").append(classRoomsToString());
        sb.append('}');
        return sb.toString();
    }
}

