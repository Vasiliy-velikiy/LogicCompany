package com.moskalev.domain.dto.impl.truckDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class TruckToCreateDto {

    @NotBlank
    private String registrationNumber;

    @NotNull
    //размер смены водителей
    private Double changeTimeOfDriver;

    @NotNull
    private Double truckCapacity;

    /**
     * This field describes serviceable or defective
     */

    @NotNull
    private Boolean workStatus;


    private  String city;


    private String driverNumber;


    private String orderNumber;

}
