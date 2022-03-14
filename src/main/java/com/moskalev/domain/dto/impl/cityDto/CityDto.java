package com.moskalev.domain.dto.impl.cityDto;

import com.moskalev.domain.entity.MapOfCountry;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CityDto {

    private String name;

    private Double coordinateX;

    private Double coordinateY;

    //private Integer mapOfCountry;
    //??
    //private Map mapOfCountry;
    private String mapOfCountry;

}
