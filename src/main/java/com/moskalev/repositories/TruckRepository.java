package com.moskalev.repositories;

import com.moskalev.domain.entity.Order;
import com.moskalev.domain.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TruckRepository extends JpaRepository<Truck,Long> {

    Optional<Truck> findByRegistrationNumber(String registrNumber);

    List<Truck>findByTruckCapacityLessThanEqualAndAndWorkStatusAndAndOrder(Double capacity, Boolean workStatus, Order order);

}
