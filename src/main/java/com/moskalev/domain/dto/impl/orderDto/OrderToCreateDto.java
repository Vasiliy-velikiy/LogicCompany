package com.moskalev.domain.dto.impl.orderDto;

import com.moskalev.domain.entity.Truck;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
public class OrderToCreateDto {

    @NotBlank
    private String uniqueNumber;

    /**
     * Completed (yes/no)
     */
    @NotNull
    private Boolean isCompleted;


}
