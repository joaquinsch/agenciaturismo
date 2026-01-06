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
public class PaqueteEdicionDTO {

    private Long codigo_producto;
    private List<Long> lista_ids_servicios_incluidos;
    private Double costo_paquete;

}
