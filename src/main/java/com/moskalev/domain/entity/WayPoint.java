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
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "city_id_for_waypoint")
    private City currentCityForWayPoint;

    /**
     * ГРУЗ
     */
    //у мар точки только 1 груз, у груза -список маршрутных точек?
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "cargo_id_for_waypoint")
    private Cargo currentCargoForWayPoint;

    /**
     * Type true- loading
     * false-unloading
     */
    @Column
    private Boolean isLoading;


    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "order_id_for_waypoint")
    private Order orderForWayPoint;

}
