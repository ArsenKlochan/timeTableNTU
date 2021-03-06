package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.BaseObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curriculums", schema = "ntu")
public class Curriculum extends BaseObject {

    @Id
    @Column(name = "curriculum_id")
    @SequenceGenerator(name = "curriculumId", sequenceName = "seq_curriculum_id", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curriculumId")
    private Long curriculumId;

    @Column(name = "curriculum_name")
    private String curriculumName;

    @Column(name = "short_name")
    private String shortName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Speciality.class)
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column (name = "qualification")
    private String qualification;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curriculum", targetEntity = Course.class)
    private List<Course> courses = new ArrayList<>();

    public Curriculum(){}

    public Curriculum(String curriculumName, String shortName, Speciality speciality, Department department, String qualification) {
        this.curriculumName = curriculumName;
        this.shortName = shortName;
        this.speciality = speciality;
        this.department = department;
        this.qualification = qualification;
    }

    public Curriculum(String curriculumName, String shortName, Speciality speciality, Department department, String qualification, List<Course> courses) {
        this.curriculumName = curriculumName;
        this.shortName = shortName;
        this.speciality = speciality;
        this.department = department;
        this.qualification = qualification;
        this.courses = courses;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }
    public String getCurriculumName() {
        return curriculumName;
    }
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }
    public Speciality getSpeciality() {
        return speciality;
    }
    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    private String courseToString(){
        StringBuilder sb = new StringBuilder();
        for(Course course: courses){
            sb.append(course.getCourseName() + "/n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Curriculum{");
        sb.append("specialityId=").append(curriculumId);
        sb.append(", specialityName='").append(curriculumName).append('\'');
        sb.append(", shortName='").append(shortName).append('\'');
        sb.append(", speciality=").append(speciality.getSpecialityName());
        sb.append(", department=").append(department.getDepartmentName());
        sb.append(", courses=").append(courseToString());
        sb.append('}');
        return sb.toString();
    }
}
