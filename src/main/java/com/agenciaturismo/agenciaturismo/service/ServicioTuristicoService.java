package com.agenciaturismo.agenciaturismo.service;


import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;

public interface ServicioTuristicoService {
    ServicioTuristico guardarServicio(ServicioTuristico servicioTuristico);
    ServicioTuristico eliminarServicio(Long codigo_producto);
    ServicioTuristico buscarServicio(Long codigo_producto);
    ServicioTuristico editarServicio(ServicioTuristico servicioTuristico);
}
