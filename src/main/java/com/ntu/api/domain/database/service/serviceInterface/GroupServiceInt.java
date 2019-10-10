package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.*;

import java.io.File;
import java.util.List;

public interface GroupServiceInt {
    Long addGroupe(Group groupe);
    Group getGroupe(Long id);
    void updateGroupe (Group groupe);
    void deleteGroupe(Group groupe);
    List<Group> getGroups();
    List<String> getParametersInString(Group group);
    List<String> getGroupOnCourse(Course course);
    List<Group> getGroupOnCourseList(Course course);
    void addGroupFromFile(File file);
}
