package com.kafka.producer.controller.mapper;

import com.kafka.producer.controller.dto.CarDTO;
import com.kafka.producer.service.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDTO carDto(Car car);

    Car car(CarDTO carDto);
}
