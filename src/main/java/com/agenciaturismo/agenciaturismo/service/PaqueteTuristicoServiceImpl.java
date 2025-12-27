package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaqueteTuristicoServiceImpl implements PaqueteTuristicoService {

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Override
    public PaqueteTuristico guardarPaquete(PaqueteTuristico paqueteTuristico) {
        return paqueteRepository.save(paqueteTuristico);
    }
}
