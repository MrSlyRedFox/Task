package ru.mrslyredfox.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.mrslyredfox.persist.model.FileDescriptor;

public interface FileDescriptionRepository extends JpaRepository<FileDescriptor,Long> {
}
