package com.ntu.api.domain.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "specialities", schema = "ntu")
public class Speciality {

    @Id
    @Column (name = "speciality_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long specialityId;

    @Column(name = "speciality_code")
    private String specialityCode;

    @Column(name = "speciality_name")
    private String specialityName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Faculty.class)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality", targetEntity = Curriculum.class)
    private List<Curriculum> curriculums = new ArrayList<>();

    public Speciality(){}

    public Speciality(String specialityCode, String specialityName, Faculty faculty) {
        this.specialityCode = specialityCode;
        this.specialityName = specialityName;
        this.faculty = faculty;
    }

    public Speciality(String specialityCode, String specialityName, Faculty faculty, List<Curriculum> curriculums) {
        this.specialityCode = specialityCode;
        this.specialityName = specialityName;
        this.faculty = faculty;
        this.curriculums = curriculums;
    }

    public Long getSpecialityId() {
        return specialityId;
    }
    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }
    public String getSpecialityCode() {
        return specialityCode;
    }
    public void setSpecialityCode(String specialityCode) {
        this.specialityCode = specialityCode;
    }
    public String getSpecialityName() {
        return specialityName;
    }
    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
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

    private String specialitiesToString(){
        StringBuilder sb = new StringBuilder();
        for(Curriculum curriculum : curriculums){
            sb.append(curriculum.getCurriculumName() + "/n");
        }
        return sb.toString();
    }

   @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Speciality{");
        sb.append("specialityId=").append(specialityId);
        sb.append(", specialityName='").append(specialityName).append('\'');
        sb.append(", faculty=").append(faculty.getFacultyName());
        sb.append(", spesialities: ").append(specialitiesToString());
        sb.append('}');
        return sb.toString();
    }
}