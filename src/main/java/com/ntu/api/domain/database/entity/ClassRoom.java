package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.BaseObject;
import com.ntu.api.domain.database.entity.enums.ClassRoomTypes;

import javax.persistence.*;

@Entity
@Table(name = "classRooms", schema = "ntu")
public class ClassRoom extends BaseObject {

    @Id
    @Column(name = "classrooms_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long classRoomsId;

    @Column(name = "classrooms_name")
    private String classRoomName;

    @Column(name = "classroom_size")
    private Integer classRoomSize;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private ClassRoomTypes type;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Building.class)
    @JoinColumn(name = "building", nullable = false)
    private Building building;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
    @JoinColumn(name = "department", nullable = false)
    private Department department;

    public ClassRoom(){}

    public ClassRoom(String classRoomName, Integer classRoomSize, ClassRoomTypes type, Building building, Department department) {
        this.classRoomName = classRoomName;
        this.classRoomSize = classRoomSize;
        this.type = type;
        this.building = building;
        this.department = department;
    }

    public Long getClassRoomsId() {
        return classRoomsId;
    }
    public void setClassRoomsId(Long classRoomsId) {
        this.classRoomsId = classRoomsId;
    }
    public String getClassRoomName() {
        return classRoomName;
    }
    public void setClassRoomName(String classRoomsName) {
        this.classRoomName = classRoomsName;
    }
    public Integer getClassRoomSize() {
        return classRoomSize;
    }
    public void setClassRoomSize(Integer classRoomSize) {
        this.classRoomSize = classRoomSize;
    }
    public ClassRoomTypes getType() {
        return type;
    }
    public void setType(ClassRoomTypes type) {
        this.type = type;
    }
    public Building getBuilding() {
        return building;
    }
    public void setBuilding(Building building) {
        this.building = building;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClassRoom{");
        sb.append("classRoomsId=").append(classRoomsId);
        sb.append(", classRoomsName='").append(classRoomName).append('\'');
        sb.append(", classRoomDescription='").append(classRoomSize).append('\'');
        sb.append(", type ").append(type);
        sb.append(", building ").append(building);
        sb.append(", department ").append(department);
        sb.append('}');
        return sb.toString();
    }
}
