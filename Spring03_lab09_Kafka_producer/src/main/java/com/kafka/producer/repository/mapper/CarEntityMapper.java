package com.kafka.producer.repository.mapper;

import com.kafka.producer.repository.entity.CarEntity;
import com.kafka.producer.service.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CarEntityMapper {
    CarEntityMapper INSTANCE = Mappers.getMapper(CarEntityMapper.class);

    CarEntity carEntity(Car car);

    Car car(CarEntity carEntity);
}
