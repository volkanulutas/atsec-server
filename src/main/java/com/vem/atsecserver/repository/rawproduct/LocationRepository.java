package com.vem.atsecserver.repository.rawproduct;

import com.vem.atsecserver.entity.rawproduct.EnumLocationType;
import com.vem.atsecserver.entity.rawproduct.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author volkanulutas
 * @since 02.01.2021
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByLocationType(EnumLocationType locationType);
}
