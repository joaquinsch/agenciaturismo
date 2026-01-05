package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.dto.VentaDTO;
import com.agenciaturismo.agenciaturismo.exceptions.ClienteInexistenteError;
import com.agenciaturismo.agenciaturismo.exceptions.EmpleadoInexistenteError;
import com.agenciaturismo.agenciaturismo.exceptions.ProductoInexistenteError;
import com.agenciaturismo.agenciaturismo.exceptions.VentaInexistenteError;
import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.model.ProductoTuristico;
import com.agenciaturismo.agenciaturismo.model.Venta;
import com.agenciaturismo.agenciaturismo.repository.ClienteRepository;
import com.agenciaturismo.agenciaturismo.repository.EmpleadoRepository;
import com.agenciaturismo.agenciaturismo.repository.ProductoRepository;
import com.agenciaturismo.agenciaturismo.repository.VentaRepository;
import org.springframework.stereotype.Service;


@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ProductoRepository productoRepository;

    public VentaServiceImpl(VentaRepository ventaRepository,
                            ClienteRepository clienteRepository,
                            EmpleadoRepository empleadoRepository,
                            ProductoRepository productoRepository
                            ) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public Venta guardarVenta(VentaDTO ventaDTO) {
        Cliente clienteBuscado = this.clienteRepository.findById(ventaDTO.getId_cliente())
                .orElseThrow(
                        () -> new ClienteInexistenteError("El cliente ingresado no existe")
                );
        Empleado empleadoBuscado = this.empleadoRepository.findById(ventaDTO.getId_empleado())
                .orElseThrow(
                        () -> new EmpleadoInexistenteError("El empleado ingresado no existe")
                );
        ProductoTuristico productoTuristicoBuscado = this.productoRepository.findById(ventaDTO.getCodigo_producto())
                .orElseThrow(
                        () -> new ProductoInexistenteError("El producto ingresado no existe")
                );

        Venta venta = Venta.builder()
                .fecha_venta(ventaDTO.getFecha_venta())
                .medio_pago(ventaDTO.getMedio_pago())
                .cliente(clienteBuscado)
                .empleado(empleadoBuscado)
                .producto_turistico(productoTuristicoBuscado)
                .build();
        return this.ventaRepository.save(venta);
    }

    @Override
    public Venta buscarVenta(Long codigo_producto) {
        return this.ventaRepository.findById(codigo_producto).orElseThrow(
                () -> new VentaInexistenteError("La venta no existe")
        );
    }

}
