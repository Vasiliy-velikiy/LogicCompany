package com.moskalev.domain.dto.impl.orderDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {


    private String uniqueNumber;

    /**
     * Completed (yes/no)
     */
    private Boolean isCompleted;
}
