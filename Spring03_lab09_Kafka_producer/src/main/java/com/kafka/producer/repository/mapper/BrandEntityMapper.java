package com.kafka.producer.repository.mapper;

import com.kafka.producer.repository.entity.BrandEntity;
import com.kafka.producer.service.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BrandEntityMapper {
    BrandEntityMapper INSTANCE = Mappers.getMapper(BrandEntityMapper.class);

    BrandEntity brandEntity(Brand brand);

    Brand brand(BrandEntity brandEntity);

}
