package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.BaseObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "specialities", schema = "ntu")
public class Speciality extends BaseObject {

    @Id
    @Column (name = "speciality_id")
    @SequenceGenerator(name = "specialityId", sequenceName = "seq_speciality_id", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialityId")
    private Long specialityId;

    @Column(name = "speciality_code")
    private String specialityCode;

    @Column(name = "speciality_name")
    private String specialityName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality", targetEntity = Curriculum.class)
    private List<Curriculum> curriculums = new ArrayList<>();

    public Speciality(){}

    public Speciality(String specialityCode, String specialityName) {
        this.specialityCode = specialityCode;
        this.specialityName = specialityName;
    }

    public Speciality(String specialityCode, String specialityName, List<Curriculum> curriculums) {
        this.specialityCode = specialityCode;
        this.specialityName = specialityName;
        this.curriculums = curriculums;
    }

    public Long getSpecialityId() {
        return specialityId;
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
    public List<Curriculum> getCurriculums() {
        return curriculums;
    }
    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }

    private String curriculumsToString(){
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
        sb.append(", spesialities: ").append(curriculumsToString());
        sb.append('}');
        return sb.toString();
    }
}