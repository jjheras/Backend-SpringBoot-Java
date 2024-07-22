package com.kafka.producer.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {
    private Integer id;
    private String name;
    private Integer warranty;
    private String country;
}
