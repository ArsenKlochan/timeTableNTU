package com.ntu.api.domain.database.entity;

import com.ntu.api.domain.BaseObject;
import com.ntu.api.domain.ObjectWithTimeTable;
import com.ntu.api.domain.database.entity.enums.ClassRoomTypes;
import com.ntu.api.domain.timeTable.TimeTableObject;

import javax.persistence.*;

@Entity
@Table(name = "classRooms", schema = "ntu")
public class ClassRoom extends BaseObject implements ObjectWithTimeTable {

    @Id
    @Column(name = "classrooms_id")
    @SequenceGenerator(name = "classroomId", sequenceName = "seq_classroom_id", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classroomId")
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

//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
//    @JoinColumn(name = "department", nullable = false)
    @Column
    private String department;

    @Transient
    private TimeTableObject timeTable;

    public ClassRoom(){}

    public ClassRoom(String classRoomName, Integer classRoomSize, ClassRoomTypes type, Building building, String department, TimeTableObject timeTable) {
        this.classRoomName = classRoomName;
        this.classRoomSize = classRoomSize;
        this.type = type;
        this.building = building;
        this.department = department;
        this.timeTable = timeTable;
    }

    public ClassRoom(String classRoomName, Integer classRoomSize, ClassRoomTypes type, Building building, String department) {
        this.classRoomName = classRoomName;
        this.classRoomSize = classRoomSize;
        this.type = type;
        this.building = building;
        this.department = department;
    }

    public ClassRoom(String classRoomName, Integer classRoomSize, ClassRoomTypes type, Building building) {
        this.classRoomName = classRoomName;
        this.classRoomSize = classRoomSize;
        this.type = type;
        this.building = building;
    }

    public Long getClassRoomsId() {
        return classRoomsId;
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
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public TimeTableObject getTimeTable() {
        return timeTable;
    }
    public void setTimeTable(TimeTableObject timeTable) {
        this.timeTable = timeTable;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClassRoom{");
        sb.append("classRoomsId=").append(classRoomsId);
        sb.append(", classRoomsName='").append(classRoomName).append('\'');
        sb.append(", classRoomDescription='").append(classRoomSize).append('\'');
        sb.append(", type ").append(type);
        sb.append(", building ").append(building.getBuildingName());
        sb.append(", department ").append(department);
        sb.append('}');
        return sb.toString();
    }
}
