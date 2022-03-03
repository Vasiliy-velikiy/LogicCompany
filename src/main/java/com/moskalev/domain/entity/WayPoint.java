package com.moskalev.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 03.03.22
 * This is class describes Way Points
 */
@Getter
@Setter
@Table(name = "WayPoint")
@Entity
public class WayPoint {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    String city;

    /**
     * ГРУЗ
     */
    Cargo currentCargo;

    /**
     * Type loading/unloading
     */
    Boolean isLoading;


}
