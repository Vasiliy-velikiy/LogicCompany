package com.moskalev.repositories;

import com.moskalev.domain.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck,Integer> {
}
