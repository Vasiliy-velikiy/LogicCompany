package com.moskalev.domain.mapper.mupstruct;

import com.moskalev.domain.entity.Cargo;
import com.moskalev.domain.entity.City;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface MergeMapperForCity {

    /**
     * @param target        -Entity for merge
     * @param source-merged object
     * @return -new Object
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    City merge(@MappingTarget City target, City source);
}
