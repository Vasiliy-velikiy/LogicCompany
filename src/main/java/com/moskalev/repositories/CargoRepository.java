package com.moskalev.repositories;

import com.moskalev.domain.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo,Long> {

    Optional<Cargo>findCargoByNumberOfCargo(String numberOfCargo);


}
