package com.moskalev.domain.mapper.mupstruct;

import com.moskalev.domain.entity.Person;
import com.moskalev.domain.entity.Truck;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface MergeMapperForTruck {

    /**
     * @param target        -Entity for merge
     * @param source-merged object
     * @return -new Object
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Truck merge(@MappingTarget Truck target, Truck source);
}
