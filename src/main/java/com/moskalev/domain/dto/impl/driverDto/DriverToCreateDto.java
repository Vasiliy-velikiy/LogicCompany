package com.moskalev.domain.dto.impl.driverDto;

import com.moskalev.domain.entity.status.StatusOfDriver;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
public class DriverToCreateDto {

    @NotBlank
    @Size(max = 30)
    private String firstName;

    @NotBlank
    @Size(max = 30)
    private String lastName;

    @NotBlank
    @Size(max = 30)
    private String personalNumber;

    @NotBlank
    private Double hoursWorkedPerMonth;

    @NotBlank
    private StatusOfDriver statusOfDriver;

    private Long currentTruckId;

    private Long cityIdForDriver;


    private Long orderId;



}
