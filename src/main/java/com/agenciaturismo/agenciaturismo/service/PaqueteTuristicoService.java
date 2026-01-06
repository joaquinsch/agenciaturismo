package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.dto.PaqueteDTO;
import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;

public interface PaqueteTuristicoService {
    PaqueteTuristico guardarPaquete(PaqueteDTO paqueteDTO);
    PaqueteTuristico buscarPaquete(Long codigo_producto);
    void eliminarPaquete(Long codigo_producto);
    PaqueteTuristico editarPaquete(PaqueteTuristico paqueteTuristico);
}
