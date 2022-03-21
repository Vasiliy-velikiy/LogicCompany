package com.moskalev.domain.dto.impl.waypointDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class WayPointDto {

    Long id;

    String currentCityForWayPoint;


    String currentCargoForWayPoint;

    /**
     * Type true- loading
     * false-unloading
     */

     Boolean isLoading;


     String orderForWayPoint;
}
