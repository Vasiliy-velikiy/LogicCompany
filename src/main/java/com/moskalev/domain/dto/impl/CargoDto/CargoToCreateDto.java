package com.moskalev.domain.dto.impl.CargoDto;

import com.moskalev.domain.entity.status.StatusOfCargo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CargoToCreateDto {

    @NotBlank
    @Size(max = 30)
    private String numberOfCargo;

    @NotBlank
    @Size(max = 30)
    private String nameOfCargo;

    /**
     * weight in kilograms
     */
    @NotBlank
    private Double weight;

    @NotNull
    private StatusOfCargo statusOfCargo;
}
