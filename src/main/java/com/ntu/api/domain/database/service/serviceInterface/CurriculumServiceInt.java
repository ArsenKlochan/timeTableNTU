package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.*;

import java.util.List;

public interface CurriculumServiceInt {
    Long addCurriculum(Curriculum curriculum);
    Curriculum getCurriculum(Long id);
    void updateCurriculum (Curriculum curriculum);
    void deleteCurriculum(Curriculum curriculum);
    List<Curriculum> getCurriculums();
}
