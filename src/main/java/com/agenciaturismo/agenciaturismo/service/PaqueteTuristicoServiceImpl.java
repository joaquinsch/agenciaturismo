package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.exceptions.CostoInvalidoError;
import com.agenciaturismo.agenciaturismo.exceptions.PaqueteInvalidoError;
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
        if (paqueteTuristico.getLista_servicios_incluidos() == null) {
            throw new PaqueteInvalidoError("El paquete no tiene servicios asociados");
        } else if (paqueteTuristico.getLista_servicios_incluidos().size() < 2) {
            throw new PaqueteInvalidoError("El paquete debe tener mas de un servicio asociado");
        }
        if (!paqueteTuristico.validarCostoDePaquete()) {
            throw new CostoInvalidoError("El costo del paquete no coincide con la suma de los servicios menos 10%");
        }
        return paqueteRepository.save(paqueteTuristico);
    }
}
