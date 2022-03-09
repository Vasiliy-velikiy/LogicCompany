package com.moskalev.domain.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 03.03.22
 * This is class describes Order
 */
@Getter
@Setter
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @Column(name = "id",updatable = false, nullable = false, unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_number")
    private String uniqueNumber;

    /**
     * Completed (yes/no)
     */
    @Column
    private Boolean isCompleted;

    /**
     * Truck assigned to fulfill the order
     */
    //фура назначеная выполнять заказ-требование в условии
    @OneToOne(fetch = LAZY, mappedBy = "order", optional = false)
    private Truck currentTruck;

    /**
     * list Of way Points :City ,Cargo, Type loading/unloading
     */
    //заказ может пройти через список маршрутных точек, а у маршрутной точки только 1 заказ
    //private List <WayPoint> wayPoints;

    /**List of drivers who fulfill the order*/

    @OneToMany(mappedBy = "orderForDriver",
            cascade = {PERSIST, MERGE})
    //	Список водителей, которые выполняют заказ-требование в условии
    private List <Driver> drivers;

}
