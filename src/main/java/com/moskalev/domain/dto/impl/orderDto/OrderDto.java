package com.moskalev.domain.dto.impl.orderDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderDto {


    private String uniqueNumber;

    /**
     * Completed (yes/no)
     */
    private Boolean isCompleted;
}
