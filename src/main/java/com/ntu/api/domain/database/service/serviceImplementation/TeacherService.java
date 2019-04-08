package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.TeacherDAOInt;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.entity.Teacher;
import com.ntu.api.domain.database.service.serviceInterface.TeacherServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
