package com.moskalev.repositories;

import com.moskalev.domain.entity.MapOfCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MapOfCountryRepository extends JpaRepository<MapOfCountry, Long> {

    Optional<MapOfCountry> findByMapName(String name);
}
