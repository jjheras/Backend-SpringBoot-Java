package com.kafka.producer.controller.mapper;

import com.kafka.producer.controller.dto.OfferDTO;
import com.kafka.producer.service.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OfferMapper {
    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    Offer offer (OfferDTO offerDTO);

    OfferDTO offerDTO (Offer offer);
}
