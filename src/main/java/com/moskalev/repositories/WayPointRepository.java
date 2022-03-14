package com.moskalev.repositories;

import com.moskalev.domain.entity.WayPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WayPointRepository extends JpaRepository<WayPoint,Integer> {
}
