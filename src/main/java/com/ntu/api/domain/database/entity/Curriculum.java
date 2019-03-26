package com.ntu.api.domain.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curriculums", schema = "ntu")
public class Curriculum {

    @Id
    @Column (name = "curriculum_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long curriculumId;

    @Column(name = "curriculum_code")
    private String curriculumCode;

    @Column(name = "curriculum_name")
    private String curriculumName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Faculty.class)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curriculum", targetEntity = Speciality.class)
    private List<Speciality> specialities = new ArrayList<>();

    public Curriculum(){}

    public Curriculum(String curriculumCode, String curriculumName, Faculty faculty) {
        this.curriculumCode = curriculumCode;
        this.curriculumName = curriculumName;
        this.faculty = faculty;
    }

    public Curriculum(String curriculumCode, String curriculumName, Faculty faculty,
                      List<Speciality> specialities) {
        this.curriculumCode = curriculumCode;
        this.curriculumName = curriculumName;
        this.faculty = faculty;
        this.specialities = specialities;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }
    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }
    public String getCurriculumCode() {
        return curriculumCode;
    }
    public void setCurriculumCode(String curriculumCode) {
        this.curriculumCode = curriculumCode;
    }
    public String getCurriculumName() {
        return curriculumName;
    }
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
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

    private String specialitiesToString(){
        StringBuilder sb = new StringBuilder();
        for(Speciality speciality : specialities){
            sb.append(speciality.getSpecialityId() + " " + speciality.getSpecialityName() + "/n");
        }
        return sb.toString();
    }

   @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Curriculum{");
        sb.append("curriculumId=").append(curriculumId);
        sb.append(", curriculumCode='").append(curriculumCode).append('\'');
        sb.append(", curriculumName='").append(curriculumName).append('\'');
        sb.append(", faculty=").append(faculty.getFacultyName());
        sb.append(", spesialities: ").append(specialitiesToString());
        sb.append('}');
        return sb.toString();
    }
}