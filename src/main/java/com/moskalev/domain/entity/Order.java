package com.moskalev.domain.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 03.03.22
 * This is class describes Order
 */
@Getter
@Setter
@Table(name = "order")
@Entity
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    Integer uniqueNumber;
    /**
     * Completed (yes/no)
     */
    @Column
    Boolean isCompleted;

    /**
     * Truck assigned to fulfill the order
     */
    Truck currentTruck;

    /**
     * list Of way Points :City ,Cargo, Type loading/unloading
     */
    @ElementCollection
    @CollectionTable(name = "someObjects",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "some_id")
    List <WayPoint> wayPoints;

    /**List of drivers who fulfill the order*/
    List <Driver> drivers;

}
