package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOinterface.CourseDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.CurriculumDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.SpecialityDAOInt;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.service.serviceInterface.CourseServiceInt;
import com.ntu.api.model.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseService implements CourseServiceInt {
    @Autowired private CourseDAOInt courseDAO;
    @Autowired private CurriculumDAOInt curriculumDAO;

    @Override
    public Long addCourse(Course course) {
        return courseDAO.create(course);
    }
    @Override
    public Course getCourse(Long id) {
        return courseDAO.get(id);
    }
    @Override
    public void updateCourse(Course course) {
        courseDAO.update(course);
    }
    @Override
    public void deleteCourse(Course course) {
        courseDAO.delete(course);
    }
    @Override
    public List<Course> getCourses() {
        return courseDAO.findAll();
    }
    @Override
    public List<String> getParametersInString(Course course){
        List<String> parameters = new ArrayList<>();
        Curriculum curriculum = curriculumDAO.get(course.getCurriculum().getCurriculumId());
        parameters.add(course.getCourseName());
        parameters.add(curriculum.getCurriculumName());
        return parameters;
    }

    @Override
    public List<String> getCourseOnCurriculuminString(Curriculum curriculum) {
        List<String> courseOnCurriculum = new ArrayList<>();
        for(Course course:curriculumDAO.get(curriculum.getCurriculumId()).getCourses()){
            courseOnCurriculum.add(course.getCourseName());
        }
        return courseOnCurriculum;
    }

    @Override
    public List<Course> getCourseOnCurriculumList(Curriculum curriculum) {
        ArrayList<Course> courses = new ArrayList<>();
        Curriculum curriculum1 = curriculumDAO.get(curriculum.getCurriculumId());
        for(Course course: curriculum1.getCourses()){
            courses.add(course);
        }
        return courses;
    }

    @Override
    public void addCourseFromFile(File file) {
        for(ArrayList<String> list: ExcelReader.excelRead(file.getAbsolutePath())){
            String name = list.get(0);
            addCourse(new Course(name,
                    Lists.getCurriculumService().getCurriculums().get(Lists.getCurriculumList().indexOf(list.get(1)))));
        }

    }
}
