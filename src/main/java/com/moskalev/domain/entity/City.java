package com.moskalev.domain.entity;

import javax.persistence.Column;

public class City {

    @Column
    String name;

    @Column
    Double coordinateX;

    @Column
    Double coordinateY;

    //?
    Driver driver;
    MapOfCountry mapOfCountry;

}
