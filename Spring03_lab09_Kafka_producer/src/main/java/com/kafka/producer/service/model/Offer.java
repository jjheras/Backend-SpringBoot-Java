package com.kafka.producer.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    private Integer idcar;
    private Integer telefono;
    private Double oferta;
    private LocalDateTime fecha_oferta;
}
