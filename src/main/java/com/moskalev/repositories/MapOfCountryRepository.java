package com.moskalev.repositories;

import com.moskalev.domain.entity.MapOfCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapOfCountryRepository extends JpaRepository<MapOfCountry, Integer> {
}
