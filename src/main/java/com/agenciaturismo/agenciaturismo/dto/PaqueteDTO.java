package com.agenciaturismo.agenciaturismo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaqueteDTO {

    private List<Long> lista_servicios_incluidos;
    private Double costo_paquete;

}
