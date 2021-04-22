package com.vem.atsecserver.repository;

import com.vem.atsecserver.entity.file.EnumFileDBType;
import com.vem.atsecserver.entity.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author volkanulutas
 * @since 03.01.2020
 */
@Repository
public interface FileDBRepository extends JpaRepository<File, Long> {
    Collection<File> findByFileDBType(EnumFileDBType fileDBType);


}
