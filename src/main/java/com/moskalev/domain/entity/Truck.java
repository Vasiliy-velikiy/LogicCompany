package com.moskalev.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

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
    private Long id;

    @Column
    private String registrationNumber;

    //размер смены водителей
    @Column
    private Double changeTimeOfDriver;

    //вместимость в тоннах
    @Column
    private Double truckCapacity;

    /**
     * This field describes serviceable or defective
     */
    @Column
    private Boolean workStatus;

    //?
   // private City currentCity;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;





}
