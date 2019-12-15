package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOinterface.CourseDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.CurriculumDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.GroupDAOInt;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.Group;
import com.ntu.api.domain.database.service.serviceInterface.GroupServiceInt;
import com.ntu.api.model.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupService implements GroupServiceInt {
    @Autowired private GroupDAOInt groupDAO;
    @Autowired private CourseDAOInt courseDAO;
    @Autowired private CurriculumDAOInt curriculumDAO;

    @Override
    public Long addGroupe(Group groupe) {
        return groupDAO.create(groupe);
    }
    @Override
    public Group getGroupe(Long id) {
        return groupDAO.get(id);
    }
    @Override
    public void updateGroupe(Group groupe) {
        groupDAO.update(groupe);
    }
    @Override
    public void deleteGroupe(Group groupe) {
        groupDAO.delete(groupe);
    }
    @Override
    public List<Group> getGroups() {
        return groupDAO.findAll();
    }
    @Override
    public List<String> getParametersInString(Group group) {
        List<String> parameters = new ArrayList<>();
        Course course = courseDAO.get(group.getCourse().getCourseId());
        Curriculum curriculum = curriculumDAO.get(course.getCurriculum().getCurriculumId());
        parameters.add(group.getGroupName());
        parameters.add(group.getStudentsNumber().toString());
        parameters.add(curriculum.getCurriculumName());
        parameters.add(course.getCourseName());
        return parameters;
    }

    @Override
    public void addGroupFromFile(File file) {
        for(ArrayList<String> list: ExcelReader.excelRead(file.getAbsolutePath())){
            String name = list.get(0);
            int description = Integer.parseInt(list.get(1));
            addGroupe(new Group(name, description,
                    Lists.getCourseService().getCourses().get(Lists.getCourseList().indexOf(list.get(2)))));
        }
    }

    @Override
    public List<String> getGroupOnCourse(Course course) {
        List<String> grouptList = new ArrayList<>();
        for(Group group: courseDAO.get(course.getCourseId()).getGroups()){
            grouptList.add(group.getGroupName());
        }
        return grouptList;
    }

    @Override
    public List<Group> getGroupOnCourseList(Course course) {
        ArrayList<Group> groups = new ArrayList<>();
        for(Group group: courseDAO.get(course.getCourseId()).getGroups()){
            groups.add(group);
        }
        return groups;
    }
}
