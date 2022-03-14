package com.moskalev.domain.dto.impl.cityDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CityToCreateDto {

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    private Double coordinateX;

    @NotBlank
    private Double coordinateY;

    @NotBlank
    private Long mapOfCountry;
}
