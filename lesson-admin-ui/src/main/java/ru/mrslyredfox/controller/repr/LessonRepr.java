package ru.mrslyredfox.controller.repr;

import java.util.List;

import ru.mrslyredfox.persist.model.FileDescriptor;
import ru.mrslyredfox.persist.model.Lesson;

public class LessonRepr {
    private Long lesson_id;

    private String lessonName;

    private String description;

    private List<FileDescriptor> fileDescriptors;

    public LessonRepr() {
    }

    public LessonRepr(Lesson lesson) {
        this.lesson_id = lesson.getLesson_id();
        this.lessonName = lesson.getLessonName();
        this.description = lesson.getDescription();
        this.fileDescriptors = lesson.getFileDescriptors();
    }

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FileDescriptor> getFileDescriptors() {
        return fileDescriptors;
    }

    public void setFileDescriptors(List<FileDescriptor> fileDescriptors) {
        this.fileDescriptors = fileDescriptors;
    }
}
