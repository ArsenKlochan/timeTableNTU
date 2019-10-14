package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.TeacherDAOInt;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.entity.Teacher;
import com.ntu.api.domain.database.entity.enums.Position;
import com.ntu.api.domain.database.service.serviceInterface.TeacherServiceInt;
import com.ntu.api.model.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherService implements TeacherServiceInt {
    @Autowired private TeacherDAOInt teacherDAO;
    @Autowired private DepartmentDAOInt departmentDAO;

    @Override
    public Long addTeacher(Teacher teacher) {
        return teacherDAO.create(teacher);
    }
    @Override
    public Teacher getTeacher(Long id) {
        return teacherDAO.get(id);
    }
    @Override
    public void updateTeacher(Teacher teacher) {
        teacherDAO.update(teacher);
    }
    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherDAO.delete(teacher);
    }
    @Override
    public List<Teacher> getTeachers() {
        return teacherDAO.findAll();
    }

    @Override
    public List<String> getParametersInString(Teacher teacher) {
        List<String> parameters = new ArrayList<>();
        Department department = departmentDAO.get(teacher.getDepartment().getDepartmentId());
        parameters.add(teacher.getTeacherSurname());
        parameters.add(teacher.getTeacherName());
        parameters.add(department.getDepartmentName());
        parameters.add(teacher.getTeacherPosition());
        return parameters;
    }

    @Override
    public List<String> getTeachersOnDepartments(Department department) {
        List<String> teacherList = new ArrayList<>();
        for(Teacher teacher: departmentDAO.get(department.getDepartmentId()).getTeachers()){
            teacherList.add(teacher.getTeacherSurname() + " " + teacher.getTeacherName());
        }
        return teacherList;
    }

    @Override
    public List<Teacher> getTeacherOnDepartmentList(Department department) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for(Teacher teacher: departmentDAO.get(department.getDepartmentId()).getTeachers()){
            teachers.add(teacher);
        }
        return teachers;
    }

    @Override
    public List<String> getTeachersByPosition(Position position) {
        List<String> teacherList = new ArrayList<>();
        for(Teacher teacher: teacherDAO.findAll()){
            if(Position.valueOf(teacher.getTeacherPosition()).name().equals(position.name())){
                teacherList.add(teacher.getTeacherSurname() + " " + teacher.getTeacherName());
            }
        }
        return teacherList;
    }

    @Override
    public List<Teacher> getTeacherByPositionList(Position position) {
        List<Teacher> teacherList = new ArrayList<>();
        for(Teacher teacher:teacherDAO.findAll()){
            if(Position.valueOf(teacher.getTeacherPosition()).name().equals(position.name())){
                teacherList.add(teacher);
            }
        }
        return teacherList;
    }

    @Override
    public void addTeacherFromFile(File file) {
        for(ArrayList<String> list: ExcelReader.excelRead(file.getAbsolutePath())){
            String surname = list.get(0);
            String name = list.get(1) + " " + list.get(2);
            addTeacher(new Teacher(surname, name,
             Lists.getDepartmentService().getDepartments().get(Lists.getDepartmentNameList().indexOf(list.get(3))),
                    Position.valueOf(list.get(4)).toString()));
        }
    }
}
