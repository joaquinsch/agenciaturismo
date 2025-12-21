package com.agenciaturismo.agenciaturismo.repositorytests;

import com.agenciaturismo.agenciaturismo.model.*;
import com.agenciaturismo.agenciaturismo.repository.ClienteRepository;
import com.agenciaturismo.agenciaturismo.repository.EmpleadoRepository;
import com.agenciaturismo.agenciaturismo.repository.ProductoRepository;
import com.agenciaturismo.agenciaturismo.repository.VentaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class VentaRepositoryTests {

    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    ProductoRepository productoRepository;

    Cliente cli = Cliente.builder()
            .id_cliente(1L)
            .build();
    Empleado empleado = Empleado.builder()
            .id_empleado(1L)
            .build();
    ProductoTuristico prod = ServicioTuristico.builder()
            .codigo_producto(1L)
            .nombre("viaje a japon")
            .descripcion_breve("un viaje unico")
            .destino_servicio("japon")
            .fecha_servicio(LocalDate.of(2026, 2, 10))
            .costo_servicio(800.0)
            .build();

    Venta venta = Venta.builder().num_venta(1L)
            .fecha_venta(LocalDate.of(2025,12,20))
            .medio_pago("tarjeta")
            .cliente(cli)
            .empleado(empleado)
            .producto_turistico(prod)
            .build();

    @Test
    public void deberiaGuardarUnaVenta(){
        Cliente cliente = Cliente.builder()
                .id_cliente(1L).build();
        clienteRepository.save(cliente);
        Empleado empleado = Empleado.builder()
                .id_empleado(1L).build();
        empleadoRepository.save(empleado);
        ProductoTuristico productoTuristico = ProductoTuristico.builder()
                .codigo_producto(1L).build();
        productoRepository.save(productoTuristico);

        Venta ventaGuardada = ventaRepository.save(venta);
        Assertions.assertNotNull(ventaGuardada.getNum_venta());

    }
}
