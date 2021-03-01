package ru.mrslyredfox.service.serviceInterface;

import java.util.List;
import java.util.Optional;

import ru.mrslyredfox.controller.repr.LessonRepr;

public interface LessonService {

    void save(LessonRepr lessonRepr);

    List<LessonRepr> findAll();

    Optional<LessonRepr> findById(Long id);

    void delete(Long id);

}
