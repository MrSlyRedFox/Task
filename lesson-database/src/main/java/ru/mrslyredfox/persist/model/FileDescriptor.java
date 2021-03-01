package ru.mrslyredfox.persist.model;


import java.text.DateFormat;
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
@Table(name = "file_descriptor_tbl")
public class FileDescriptor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "file_Name")
    private String fileName;

    @Column(name = "path_File")
    private String pathFile;

    @Column(name = "upload_Date")
    private DateFormat uploadDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "lessons_files",
    joinColumns = @JoinColumn(name = "file_id"),
    inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private List<Lesson> lessons;

    public FileDescriptor() {
    }

    public FileDescriptor(String fileName, String pathFile, DateFormat uploadDate, List<Lesson> lessons) {
        this.fileName = fileName;
        this.pathFile = pathFile;
        this.uploadDate = uploadDate;
        this.lessons = lessons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public DateFormat getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(DateFormat uploadDate) {
        this.uploadDate = uploadDate;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
