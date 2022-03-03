package com.moskalev.repositories;

import com.moskalev.domain.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck,Integer> {
}
