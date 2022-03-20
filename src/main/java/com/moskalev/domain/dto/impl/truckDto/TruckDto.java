package com.moskalev.domain.dto.impl.truckDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TruckDto {

     String registrationNumber;

    //размер смены водителей
    Double changeTimeOfDriver;


     Double truckCapacity;

    /**
     * This field describes serviceable or defective
     */
     Boolean workStatus;


      String city;

     String driverNumber;

     String orderNumber;
}
