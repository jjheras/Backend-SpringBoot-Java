package com.kafka.producer.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.producer.controller.dto.CarDTO;
import com.kafka.producer.controller.dto.OfferDTO;
import com.kafka.producer.controller.mapper.CarMapper;
import com.kafka.producer.controller.mapper.OfferMapper;
import com.kafka.producer.repository.CarRepository;
import com.kafka.producer.repository.entity.CarEntity;
import com.kafka.producer.repository.mapper.CarEntityMapper;
import com.kafka.producer.service.MessageService;
import com.kafka.producer.service.model.Car;
import com.kafka.producer.service.model.Offer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final CarMapper carMapper;
    private final CarEntityMapper carEntityMapper;
    private final OfferMapper offerMapper;
    private final CarRepository carRepository;
    @Override
    @KafkaListener(topics = "cars-offers" , groupId = "1")
    public void sendOffer(OfferDTO offerDTO) {
        try{
            Offer offer = offerMapper.offer(offerDTO);
            List<Car> cars = carRepository.findAll().stream()
                    .map(carEntityMapper::car)
                    .collect(Collectors.toList());
            //verificiar si el id existe
            boolean idCarExist = cars.stream().anyMatch(car -> car.getId().equals(offer.getIdcar()));
            if(!idCarExist){
                throw new IllegalArgumentException("El id no existe.");
            }

            //convertir offerDTO a String
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOffer = objectMapper.writeValueAsString(offerDTO);
            //enviar mensaje
            kafkaTemplate.send("cars-offers",jsonOffer);
            log.info("Mensaje enviado a Kafka: {}", jsonOffer);
        }catch (Exception e){
            log.error("Error al enviar el mensaje a Kafka", e);
            throw new RuntimeException("Error al enviar el mensaje a Kafka", e);
        }

    }


    @Override
    public List<CarDTO> getAllCarsOffers() {
        try {
            List<CarEntity> carEntityList = carRepository.findAll();
            List<CarDTO> carDTOList = carEntityList.stream()
                    .map(carEntityMapper::car)
                    .map(carMapper::carDto)
                    .collect(Collectors.toList());
            return carDTOList;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los coches en oferta", e);
        }
    }
}
