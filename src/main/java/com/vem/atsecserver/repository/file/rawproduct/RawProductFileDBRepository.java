package com.vem.atsecserver.repository.file.rawproduct;

import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.entity.report.rawproduct.EnumRawProductFileDBType;
import com.vem.atsecserver.entity.report.rawproduct.RawProductFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author volkanulutas
 * @since 03.01.2020
 */
@Repository
public interface RawProductFileDBRepository extends JpaRepository<RawProductFile, Long> {
    Collection<RawProductFile> findByFileDBType(EnumRawProductFileDBType fileDBType);
    Stream<RawProductFile> findByRawProduct(RawProduct rawProduct);
}
