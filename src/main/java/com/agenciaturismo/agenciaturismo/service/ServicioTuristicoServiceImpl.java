package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioTuristicoServiceImpl implements ServicioTuristicoService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public ServicioTuristico guardarServicio(ServicioTuristico servicioTuristico) {
        return this.servicioRepository.save(servicioTuristico);
    }
}
