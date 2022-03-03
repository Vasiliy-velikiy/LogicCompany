package com.moskalev.repositories;

import com.moskalev.domain.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo,Integer> {
}
