package com.moskalev.domain.dto.impl.driverDto;

import com.moskalev.domain.entity.status.StatusOfDriver;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class DriverDto {

    private String firstName;

    private String lastName;

    private String personalNumber;

    private Double hoursWorkedPerMonth;

    private StatusOfDriver statusOfDriver;

    private String currentCityForDriver;

    private String currentNumberOfTruckForDriver;

    private String currentOrderOfNumber;
}
