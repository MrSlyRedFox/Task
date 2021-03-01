package ru.mrslyredfox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.mrslyredfox.controller.repr.LessonRepr;
import ru.mrslyredfox.persist.model.Lesson;
import ru.mrslyredfox.persist.repo.LessonRepository;
import ru.mrslyredfox.service.serviceInterface.LessonService;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void save(LessonRepr lessonRepr) {
        Lesson lesson = new Lesson();
        lesson.setLesson_id(lessonRepr.getLesson_id());
        lesson.setLessonName(lessonRepr.getLessonName());
        lesson.setDescription(lessonRepr.getDescription());
        lesson.setFileDescriptors(lessonRepr.getFileDescriptors());
        lessonRepository.save(lesson);
    }

    @Override
    public List<LessonRepr> findAll() {
        return lessonRepository.findAll().stream()
                .map(LessonRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LessonRepr> findById(Long id) {
        return lessonRepository.findById(id).map(LessonRepr::new);
    }

    @Override
    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }
}
