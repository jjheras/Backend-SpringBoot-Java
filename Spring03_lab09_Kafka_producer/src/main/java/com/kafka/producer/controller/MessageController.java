package com.kafka.producer.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.producer.controller.dto.CarDTO;
import com.kafka.producer.controller.dto.OfferDTO;
import com.kafka.producer.service.MessageService;
import com.kafka.producer.service.impl.MessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestBody OfferDTO offerDTO){
        try {
            messageService.sendOffer(offerDTO);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id del coche no existe.");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El id del coche no existe.");
        }
    }

    // Método para obtener coches en oferta
    @GetMapping("/carsOffers")
    public ResponseEntity<?> showCarsOffers() {
        try {
            log.info("Iniciando la solicitud para obtener los coches en oferta");
            List<CarDTO> carsOnOffer = messageService.getAllCarsOffers();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "CarOffers.json");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(carsOnOffer);
            return new ResponseEntity<>(json.getBytes(), headers, HttpStatus.OK);
        }catch (Exception e){
            log.error("Error al obtener los coches en oferta o al generar el archivo JSON", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los coches en oferta o al generar el archivo JSON");
        }
    }

}
