package com.moskalev.domain.mapper.mupstruct;

import com.moskalev.domain.entity.Cargo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface MergeMapperForCargo {

    /**
     * @param target        -Entity for merge
     * @param source-merged object
     * @return -new Object
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cargo merge(@MappingTarget Cargo target, Cargo source);
}

