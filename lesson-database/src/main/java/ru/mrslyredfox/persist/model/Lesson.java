package ru.mrslyredfox.persist.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lesson_tbl")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long lesson_id;
    @Column(name = "lesson_Name")
    private String lessonName;
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "lessons_files",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<FileDescriptor> fileDescriptors;

    public Lesson(){
    }

    public Lesson(String lessonName, String description, List<FileDescriptor> fileDescriptors) {
        this.lessonName = lessonName;
        this.description = description;
        this.fileDescriptors = fileDescriptors;
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
