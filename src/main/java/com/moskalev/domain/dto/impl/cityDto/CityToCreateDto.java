package com.moskalev.domain.dto.impl.cityDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CityToCreateDto {

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotNull
    private Double coordinateX;

    @NotNull
    private Double coordinateY;

    @NotBlank
    private String mapOfCountry;
}
