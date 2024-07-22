package com.kafka.producer.service;

import com.kafka.producer.controller.dto.CarDTO;
import com.kafka.producer.controller.dto.OfferDTO;

import java.util.List;

public interface MessageService {
    void sendOffer(OfferDTO offerDTO);
    List<CarDTO> getAllCarsOffers();
}
