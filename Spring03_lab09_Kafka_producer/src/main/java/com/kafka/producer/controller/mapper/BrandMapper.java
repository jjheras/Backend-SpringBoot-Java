package com.kafka.producer.controller.mapper;

import com.kafka.producer.controller.dto.BrandDTO;
import com.kafka.producer.service.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDTO brandDto(Brand brand);

    Brand brand(BrandDTO brandDTO);
}
