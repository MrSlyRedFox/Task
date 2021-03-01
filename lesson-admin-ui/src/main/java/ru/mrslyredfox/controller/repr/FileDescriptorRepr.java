package ru.mrslyredfox.controller.repr;

import java.text.DateFormat;
import java.util.List;

import ru.mrslyredfox.persist.model.FileDescriptor;
import ru.mrslyredfox.persist.model.Lesson;

public class FileDescriptorRepr {

    private Long file_id;

    private String fileName;

    private String pathFile;

    private DateFormat uploadDate;

    private List<Lesson> lessons;

    public FileDescriptorRepr() {
    }

    public FileDescriptorRepr(FileDescriptor fileDescriptor) {
        this.file_id = fileDescriptor.getId();
        this.fileName = fileDescriptor.getFileName();
        this.pathFile = fileDescriptor.getPathFile();
        this.uploadDate = fileDescriptor.getUploadDate();
        this.lessons = fileDescriptor.getLessons();
    }

    public Long getFile_id() {
        return file_id;
    }

    public void setFile_id(Long file_id) {
        this.file_id = file_id;
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
