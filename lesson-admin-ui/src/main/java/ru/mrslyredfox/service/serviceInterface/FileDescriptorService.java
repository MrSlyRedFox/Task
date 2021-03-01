package ru.mrslyredfox.service.serviceInterface;

import java.util.List;
import java.util.Optional;

import ru.mrslyredfox.controller.repr.FileDescriptorRepr;

public interface FileDescriptorService {
    List<FileDescriptorRepr> findAll();

    Optional<FileDescriptorRepr> findById(Long id);

    void deleteById(Long id);

    void save(FileDescriptorRepr fileDescriptorRepr);
}
