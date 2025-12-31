package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.exceptions.CostoInvalidoError;
import com.agenciaturismo.agenciaturismo.exceptions.FechaInvalidaError;
import com.agenciaturismo.agenciaturismo.exceptions.ServicioInexistenteError;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ServicioTuristicoServiceImpl implements ServicioTuristicoService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public ServicioTuristico guardarServicio(ServicioTuristico servicioTuristico) {
        if (servicioTuristico.getCosto_servicio() == null || servicioTuristico.getCosto_servicio() < 0) {
            throw new CostoInvalidoError("El costo es inválido");
        } else if (servicioTuristico.getFecha_servicio().isBefore(LocalDate.now())) {
            throw new FechaInvalidaError("La fecha ingresada es inválida");
        }
        return this.servicioRepository.save(servicioTuristico);
    }

    @Override
    public void eliminarServicio(Long codigo_producto) {
        buscarServicio(codigo_producto);
        this.servicioRepository.deleteById(codigo_producto);
    }

    @Override
    public ServicioTuristico buscarServicio(Long codigo_producto) {
        return this.servicioRepository.findById(codigo_producto).orElseThrow(
                () ->
                new ServicioInexistenteError("El servicio buscado no existe")
        );
    }

    @Override
    public ServicioTuristico editarServicio(ServicioTuristico servicioTuristico) {
        ServicioTuristico buscado = this.buscarServicio(servicioTuristico.getCodigo_producto());
        buscado.setCodigo_producto(servicioTuristico.getCodigo_producto());
        buscado.setNombre(servicioTuristico.getNombre());
        buscado.setDescripcion_breve(servicioTuristico.getDescripcion_breve());
        buscado.setDestino_servicio(servicioTuristico.getDestino_servicio());
        buscado.setFecha_servicio(servicioTuristico.getFecha_servicio());
        buscado.setCosto_servicio(servicioTuristico.getCosto_servicio());
        return this.servicioRepository.save(buscado);
    }
}
