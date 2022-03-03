package com.moskalev.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 03.03.22
 * This is class describes Truck
 */
@Getter
@Setter
@Table(name = "truck")
@Entity
public class Truck {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    String registrationNumber;

    @Column
    Double changeTimeOfDriver;

    @Column
    Double truckCapacity;

    /**
     * This field describes serviceable or defective
     */
    @Column
    Boolean workStatus;

    //?
    City currentCity;

    //?
    Driver driver;




}
