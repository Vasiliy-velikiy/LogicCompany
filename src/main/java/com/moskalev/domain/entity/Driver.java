package com.moskalev.domain.entity;

import com.moskalev.domain.entity.status.StatusOfDriver;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.EnumType.STRING;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 03.03.22
 * This is class describes Driver
 */
@Getter
@Setter
@Table(name = "driver")
@Entity
public class Driver {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    String personalNumber;

    @Column
    Double hoursWorkedPerMonth;

    @Column
    @Enumerated(STRING)
    StatusOfDriver statusOfDriver;


    City currentCity;

    Truck currentTruck;


    //?
    List<Order> orders;



}
