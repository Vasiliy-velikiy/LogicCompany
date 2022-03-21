package com.moskalev.domain.dto.impl.waypointDto;

import com.moskalev.domain.entity.Cargo;
import com.moskalev.domain.entity.City;
import com.moskalev.domain.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class WayPointToCreateDto {

    @NotBlank
    private String currentCityForWayPoint;

    @NotBlank
    private String currentCargoForWayPoint;

    /**
     * Type true- loading
     * false-unloading
     */
    @NotBlank
    private Boolean isLoading;

    @NotBlank
    private String orderForWayPoint;
}
