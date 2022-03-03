package com.moskalev.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 03.03.22
 * This is class describes map of country
 */
@Getter
@Setter
@Table(name = "driver")
@Entity
public class MapOfCountry {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    List<City> cities;

    List<Double> distances;
}
