package com.moskalev.repositories;

import com.moskalev.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 01.03.22
 * Class repository for Person
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * @param email that is unique
     * @return object of Optional for  null-safety
     */
    Optional<Person> findByEmail(String email);
}