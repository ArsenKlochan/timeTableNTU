package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.SubjectDAOInt;
import com.ntu.api.domain.database.entity.Subjects;
import com.ntu.api.domain.database.service.serviceInterface.SubjectServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SubjectService implements SubjectServiceInt {
    @Autowired
    private SubjectDAOInt subjectDAO;

    @Override
    public Long addSubject(Subjects subject) {
        return subjectDAO.create(subject);
    }

    @Override
    public Subjects getSubject(Long id) {
        return subjectDAO.get(id);
    }

    @Override
    public void updateSubject(Subjects subject) {
        subjectDAO.update(subject);
    }

    @Override
    public void deleteSubject(Subjects subject) {
        subjectDAO.delete(subject);
    }

    @Override
    public List<Subjects> getSubjectList() {
        return subjectDAO.findAll();
    }
}
