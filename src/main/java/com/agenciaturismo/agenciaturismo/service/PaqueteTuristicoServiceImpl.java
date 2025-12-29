package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.dto.PaqueteDTO;
import com.agenciaturismo.agenciaturismo.exceptions.CostoInvalidoError;
import com.agenciaturismo.agenciaturismo.exceptions.PaqueteInvalidoError;
import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.repository.PaqueteRepository;
import com.agenciaturismo.agenciaturismo.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaqueteTuristicoServiceImpl implements PaqueteTuristicoService {

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    /*
    hay que arreglar la validacion del costo del paquete, recuperando
    cada servicio del paquete para poder obtener su costo
     */

    @Override
    public PaqueteTuristico guardarPaquete(PaqueteDTO paqueteDTO) {
        List<ServicioTuristico> servicios = servicioRepository.findAllById(paqueteDTO.getIds_servicios_incluidos());

        if (servicios.size() != paqueteDTO.getIds_servicios_incluidos().size()) {
            throw new PaqueteInvalidoError("Hay servicios turísticos inexistentes");
        } else if (servicios.isEmpty()) {
            throw new PaqueteInvalidoError("El paquete no tiene servicios asociados");
        } else if (servicios.size() < 2) {
            throw new PaqueteInvalidoError("El paquete debe tener mas de un servicio asociado");
        }
        PaqueteTuristico paquete = PaqueteTuristico.builder()
                .lista_servicios_incluidos(servicios)
                .costo_paquete(paqueteDTO.getCosto_paquete())
                .build();
        if (!paquete.validarCostoDePaquete()) {
            throw new CostoInvalidoError("El costo del paquete no coincide con la suma de los servicios menos 10%");
        }
        return paqueteRepository.save(paquete);
    }
}
