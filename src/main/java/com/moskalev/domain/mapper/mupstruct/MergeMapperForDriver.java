package com.moskalev.domain.mapper.mupstruct;

import com.moskalev.domain.entity.Driver;
import com.moskalev.domain.entity.Order;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface MergeMapperForDriver {

    /**
     * @param target        -Entity for merge
     * @param source-merged object
     * @return -new Object
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Driver merge(@MappingTarget Driver target,   Driver source);
}
