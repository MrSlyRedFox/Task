package ru.mrslyredfox.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.mrslyredfox.controller.repr.FileDescriptorRepr;
import ru.mrslyredfox.persist.model.FileDescriptor;
import ru.mrslyredfox.persist.repo.FileDescriptionRepository;
import ru.mrslyredfox.service.serviceInterface.FileDescriptorService;

@Service
public class FileDescriptorImpl implements FileDescriptorService {
    private static final Logger logger = LoggerFactory.getLogger(FileDescriptorImpl.class);

    private final FileDescriptionRepository fileDescriptionRepository;

    @Autowired
    public FileDescriptorImpl(FileDescriptionRepository fileDescriptionRepository) {
        this.fileDescriptionRepository = fileDescriptionRepository;
    }


    @Override
    public List<FileDescriptorRepr> findAll() {
        return fileDescriptionRepository.findAll().stream()
                .map(FileDescriptorRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FileDescriptorRepr> findById(Long id) {
        return fileDescriptionRepository.findById(id).map(FileDescriptorRepr::new);
    }

    @Override
    public void deleteById(Long id) {
        fileDescriptionRepository.deleteById(id);
    }

    @Override
    public void save(FileDescriptorRepr fileDescriptorRepr) {
        FileDescriptor fileDescriptor = new FileDescriptor();
        fileDescriptor.setId(fileDescriptorRepr.getFile_id());
        fileDescriptor.setFileName(fileDescriptorRepr.getFileName());
        fileDescriptor.setPathFile(fileDescriptorRepr.getPathFile());
        fileDescriptor.setUploadDate(fileDescriptorRepr.getUploadDate());
        fileDescriptionRepository.save(fileDescriptor);
    }
}
