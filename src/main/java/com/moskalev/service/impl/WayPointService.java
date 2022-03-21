package com.moskalev.service.impl;

import com.moskalev.domain.dto.impl.truckDto.TruckDto;
import com.moskalev.domain.dto.impl.truckDto.TruckToCreateDto;
import com.moskalev.domain.dto.impl.waypointDto.WayPointDto;
import com.moskalev.domain.dto.impl.waypointDto.WayPointToCreateDto;
import com.moskalev.domain.entity.Truck;
import com.moskalev.domain.entity.WayPoint;
import com.moskalev.domain.mapper.WayMapper;
import com.moskalev.domain.mapper.mupstruct.MergeMapperForWay;
import com.moskalev.exception.CustomException;
import com.moskalev.repositories.WayPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WayPointService {

    private final WayPointRepository wayPointRepository;
    private final WayMapper wayMapper;
    private final MergeMapperForWay mergeMapperForWay;

    public void create(WayPointDto dto) {
        Optional<WayPoint> wayPointOptional = wayPointRepository.findById(dto.getId());
        if(!wayPointOptional.isPresent()){
            wayPointRepository.save(wayMapper.fromDto(dto));
        }else {
            throw new CustomException(String.format("WayPoint with id : %s already exist ", dto.getId()));
        }
    }

    public void delete(Long id) {
        Optional<WayPoint> wayPointOptional = wayPointRepository.findById(id);
        if (wayPointOptional.isPresent()) {
            wayPointRepository.deleteById(wayPointOptional.get().getId());
        } else {
            throw new CustomException(String.format("WayPoint with id  : %s not found ",  id));
        }
    }

    public  WayPointDto read(Long id) {
        Optional<WayPoint> wayPointOptional = wayPointRepository.findById(id);
        if (wayPointOptional.isPresent()) {
            WayPoint wayPoint = wayPointOptional.get();
            //  Hibernate.initialize(cargo.getWayPointList());
            WayPointDto wayPointDto = wayMapper.toDto(wayPoint);
            return wayPointDto;
        } else {
            throw new CustomException(String.format("WayPoint with id  : %s not found ", id));
        }
    }

    public List<WayPointDto> readAll() {
        List<WayPointDto> wayPointDtos = wayMapper.convertListToDto(wayPointRepository.findAll());
        return wayPointDtos;
    }

    public void update(Long id, WayPointDto wayPointDto) {
        Optional<WayPoint> wayPointOptional = wayPointRepository.findById(id);
        if (wayPointOptional.isPresent()) {
            WayPoint target = wayPointOptional.get();
            WayPoint  source = wayMapper.fromDto(wayPointDto);
            wayPointRepository.save(mergeMapperForWay.merge(target, source));
        } else {
            throw new CustomException(String.format("WayPoint with id : %s not found ", id));
        }
    }
}
