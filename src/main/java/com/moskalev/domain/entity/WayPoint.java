package com.moskalev.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 03.03.22
 * This is class describes Way Points
 */
@Getter
@Setter
@Table(name = "way_point")
@Entity
public class WayPoint {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    //у маршрутной точки только 1 город, на стороне города -список маршрутных точек, так как город
    //может принадлижать списку маршрутных точек
   // private City city;

    /**
     * ГРУЗ
     */
    //у мар точки только 1 груз, у груза -список маршрутных точек?
   // private Cargo currentCargo;

    /**
     * Type loading/unloading
     */
    @Column
    private Boolean isLoading;

    //так как заказ имеет список маршрутных точек-то маршрутная точка имеет 1 заказ?
   // private List<Order> orderList;
    //может ли у маршрутной точки быть только 1 заказ?
   // private Order order;

}
