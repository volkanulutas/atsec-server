package com.vem.atsecserver.repository.city;

import com.vem.atsecserver.entity.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author volkanulutas
 * @since 03.01.2020
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
