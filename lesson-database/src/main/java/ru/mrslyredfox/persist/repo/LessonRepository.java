package ru.mrslyredfox.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.mrslyredfox.persist.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
