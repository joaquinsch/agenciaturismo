package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.exceptions.CostoInvalidoError;
import com.agenciaturismo.agenciaturismo.exceptions.EdicionInvalidaError;
import com.agenciaturismo.agenciaturismo.exceptions.FechaInvalidaError;
import com.agenciaturismo.agenciaturismo.exceptions.ServicioInexistenteError;
import com.agenciaturismo.agenciaturismo.model.IProveedorDeFecha;
import com.agenciaturismo.agenciaturismo.model.ProductoTuristico;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.repository.ServicioRepository;
import org.springframework.stereotype.Service;

@Service
public class ServicioTuristicoServiceImpl implements ServicioTuristicoService {

    private final ServicioRepository servicioRepository;
    private final IProveedorDeFecha proveedor;

    public ServicioTuristicoServiceImpl(ServicioRepository servicioRepository, IProveedorDeFecha proveedor) {
        this.servicioRepository = servicioRepository;
        this.proveedor = proveedor;
    }

    @Override
    public ServicioTuristico guardarServicio(ServicioTuristico servicioTuristico) {
        if (servicioTuristico.getCosto_servicio() == null || servicioTuristico.getCosto_servicio() < 0) {
            throw new CostoInvalidoError("El costo es inválido");
        } else if (proveedor.esFechaPasada(servicioTuristico.getFecha_servicio())) {
            throw new FechaInvalidaError("La fecha ingresada es inválida");
        }
        servicioTuristico.setTipo_producto(ProductoTuristico.TipoProducto.SERVICIO);
        servicioTuristico.setEstado(ProductoTuristico.Estado.ACTIVO);
        return this.servicioRepository.save(servicioTuristico);
    }

    @Override
    public ServicioTuristico eliminarServicio(Long codigo_producto) {
        ServicioTuristico buscado = buscarServicio(codigo_producto);
        buscado.setEstado(ProductoTuristico.Estado.ELIMINADO);
        return servicioRepository.save(buscado);
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
        if (buscado.getEstado().equals(ProductoTuristico.Estado.ELIMINADO)) {
            throw new EdicionInvalidaError("El servicio fue eliminado");
        }
        buscado.setCodigo_producto(servicioTuristico.getCodigo_producto());
        buscado.setNombre(servicioTuristico.getNombre());
        buscado.setDescripcion_breve(servicioTuristico.getDescripcion_breve());
        buscado.setDestino_servicio(servicioTuristico.getDestino_servicio());
        buscado.setFecha_servicio(servicioTuristico.getFecha_servicio());
        buscado.setCosto_servicio(servicioTuristico.getCosto_servicio());
        buscado.setEstado(ProductoTuristico.Estado.ACTIVO);
        return this.servicioRepository.save(buscado);
    }
}
