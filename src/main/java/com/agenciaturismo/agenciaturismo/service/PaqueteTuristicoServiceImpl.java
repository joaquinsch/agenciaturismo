package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.dto.PaqueteEdicionDTO;
import com.agenciaturismo.agenciaturismo.dto.PaqueteDTO;
import com.agenciaturismo.agenciaturismo.exceptions.CostoInvalidoError;
import com.agenciaturismo.agenciaturismo.exceptions.PaqueteInexistenteError;
import com.agenciaturismo.agenciaturismo.exceptions.PaqueteInvalidoError;
import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.model.ProductoTuristico;
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

    @Override
    public PaqueteTuristico guardarPaquete(PaqueteDTO paqueteDTO) {
        List<ServicioTuristico> servicios_incluidos = servicioRepository.findAllById(paqueteDTO.getLista_ids_servicios_incluidos());
        validarServiciosDelPaquete(paqueteDTO.getLista_ids_servicios_incluidos(), servicios_incluidos);

        PaqueteTuristico paquete = PaqueteTuristico.builder()
                .lista_servicios_incluidos(servicios_incluidos)
                .costo_paquete(paqueteDTO.getCosto_paquete())
                .estado(PaqueteTuristico.Estado.ACTIVO)
                .tipo_producto(ProductoTuristico.TipoProducto.PAQUETE)
                .build();
        validarCostoDePaquete(paquete);
        return paqueteRepository.save(paquete);
    }

    @Override
    public PaqueteTuristico buscarPaquete(Long codigo_producto) {
        return this.paqueteRepository.findById(codigo_producto).orElseThrow(
                () ->
                        new PaqueteInexistenteError("El paquete buscado no existe")
        );
    }

    @Override
    public PaqueteTuristico eliminarPaquete(Long codigo_producto) {
        PaqueteTuristico buscado = buscarPaquete(codigo_producto);
        buscado.setEstado(PaqueteTuristico.Estado.ELIMINADO);
        return this.paqueteRepository.save(buscado);
    }

    @Override
    public PaqueteTuristico editarPaquete(PaqueteEdicionDTO paqueteEdicionDTO) {
        PaqueteTuristico buscado = buscarPaquete(paqueteEdicionDTO.getCodigo_producto());
        List<ServicioTuristico> servicios_incluidos = servicioRepository.findAllById(paqueteEdicionDTO.getLista_ids_servicios_incluidos());
        validarServiciosDelPaquete(paqueteEdicionDTO.getLista_ids_servicios_incluidos(), servicios_incluidos);

        PaqueteTuristico paqueteEditado = PaqueteTuristico.builder()
                .codigo_producto(buscado.getCodigo_producto())
                .lista_servicios_incluidos(servicios_incluidos)
                .costo_paquete(paqueteEdicionDTO.getCosto_paquete())
                .tipo_producto(ProductoTuristico.TipoProducto.PAQUETE)
                .estado(ProductoTuristico.Estado.ACTIVO)
                .build();
        validarCostoDePaquete(paqueteEditado);

        return paqueteRepository.save(paqueteEditado);
    }

    private void validarServiciosDelPaquete(List<Long> lista_ids_servicios_incluidos, List<ServicioTuristico> servicios_recuperados) {
        if (lista_ids_servicios_incluidos.size() != servicios_recuperados.size()) {
            throw new PaqueteInvalidoError("Hay servicios turísticos inexistentes");
        } else if (lista_ids_servicios_incluidos.isEmpty()) {
            throw new PaqueteInvalidoError("El paquete no tiene servicios asociados");
        } else if (lista_ids_servicios_incluidos.size() < 2) {
            throw new PaqueteInvalidoError("El paquete debe tener mas de un servicio asociado");
        }
    }
    private void validarCostoDePaquete(PaqueteTuristico paqueteTuristico) {
        if (!paqueteTuristico.validarCostoDePaquete())
            throw new CostoInvalidoError("El costo del paquete no coincide con la suma de los servicios menos 10%");
    }
}
