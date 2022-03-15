package com.moskalev.domain.mapper.mupstruct;

import com.moskalev.domain.entity.Person;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author Vasiliy Moskalev
 * @version 1.1
 * @since 09.03.22
 * Class mapper for conversion Person to PersonToUpdateDto and PersonToCreateDto and back
 */
@Mapper
public interface MergePersonMapper {

    /**
     * @param target        -Entity for merge
     * @param source-merged object
     * @return -new Object
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Person merge(@MappingTarget Person target, Person source);
}