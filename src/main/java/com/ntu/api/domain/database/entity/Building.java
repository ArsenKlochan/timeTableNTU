package com.ntu.api.domain.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buildings", schema = "ntu")
public class Building {

    @Id
    @Column(name = "building_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long buildingId;

    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "building_adress")
    private String buildingAdress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "building", targetEntity = ClassRoom.class)
    private List<ClassRoom> classRooms= new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "building", targetEntity = Faculty.class)
    private List<Faculty> faculties= new ArrayList<>();



    public Building(){}

    public Building(String buildingName, String buildingAdress) {
        this.buildingName = buildingName;
        this.buildingAdress = buildingAdress;
    }

    public Building(String buildingName, String buildingAdress, List<ClassRoom> classRooms, List<Faculty> faculties) {
        this.buildingName = buildingName;
        this.buildingAdress = buildingAdress;
        this.classRooms = classRooms;
        this.faculties = faculties;
    }

    public Long getBuildingId() {
        return buildingId;
    }
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getBuildingAdress() {
        return buildingAdress;
    }
    public void setBuildingAdress(String buildingAdress) {
        this.buildingAdress = buildingAdress;
    }
    public List<ClassRoom> getClassRooms() {
        return classRooms;
    }
    public void setClassRooms(List<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }
    public List<Faculty> getFaculties() {
        return faculties;
    }
    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    private String classRoomsToString(){
        StringBuilder sb = new StringBuilder();
        for(ClassRoom classRoom: classRooms){
            sb.append(classRoom.getType() + " " + classRoom.getClassRoomName() + "/n");
        }
        return sb.toString();
    }

    private String facultyToString(){
        StringBuilder sb = new StringBuilder();
        for(Faculty faculty: faculties){
            sb.append(faculty.getFacultyName() + "/n");
        }
        return sb.toString();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Building{");
        sb.append("departmentId=").append(buildingId);
        sb.append(", buildingName='").append(buildingName).append('\'');
        sb.append(", buildingAdress='").append(buildingAdress).append('\'');
        sb.append(", classRooms=").append(classRoomsToString()).append('\'');
        sb.append(", buildingDescription='").append(facultyToString());
        sb.append('}');
        return sb.toString();
    }
}
