package com.kafka.producer.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarBrandDTO {
    private Integer id;
    private BrandDTO brand;
    private String model;
    private Integer year;
    private Integer numberofdoors;
    private Boolean isconvertible;
    private Integer mileage;
    private Double price;
    private String description;
    private String colour;
    private String fueltype;
}

