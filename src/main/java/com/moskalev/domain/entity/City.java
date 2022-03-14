package com.moskalev.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 03.03.22
 * This is class describes Cargo
 */
@Getter
@Setter
@Table(name = "city")
@Entity
public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double coordinateX;

    @Column
    private Double coordinateY;


    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "map_of_country_id")
    private MapOfCountry mapOfCountry;


    @OneToMany(mappedBy = "currentCityforDriver",
            orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Driver> driverList;

    @OneToMany(mappedBy = "currentCityforTruck",
            orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Truck> trucksList;


    //любой город может принадлежать списку маршрутных точек-но у маршрутной точки только 1 город
    @OneToMany(mappedBy = "currentCityForWayPoint",
            orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List <WayPoint>wayPointsList;
}
