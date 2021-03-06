package com.moskalev.domain.dto.impl.CargoDto;

import com.moskalev.domain.entity.WayPoint;
import com.moskalev.domain.entity.status.StatusOfCargo;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CargoDto {

    @Size(max = 30)
    private String numberOfCargo;

    @Size(max = 30)
    private String nameOfCargo;

    /**
     * weight in kilograms
     */
    private Double weight;

    private StatusOfCargo statusOfCargo;

    //private List<WayPoint> wayPoints;
}
