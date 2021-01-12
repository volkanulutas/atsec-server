package com.vem.atsecserver.repository;

import com.vem.atsecserver.entity.file.EnumFileDBType;
import com.vem.atsecserver.entity.file.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author volkanulutas
 * @since 03.01.2020
 */
@Repository
public interface FileDBRepository extends JpaRepository<FileDB, Long> {
    Collection<FileDB> findByFileDBType(EnumFileDBType fileDBType);
}
