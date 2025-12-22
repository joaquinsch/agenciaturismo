package com.agenciaturismo.agenciaturismo.repositorytests;

import com.agenciaturismo.agenciaturismo.model.*;
import com.agenciaturismo.agenciaturismo.repository.ClienteRepository;
import com.agenciaturismo.agenciaturismo.repository.EmpleadoRepository;
import com.agenciaturismo.agenciaturismo.repository.ProductoRepository;
import com.agenciaturismo.agenciaturismo.repository.VentaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
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
            .build();
    Empleado empleado = Empleado.builder()

            .build();
    ProductoTuristico prod = ServicioTuristico.builder()

            .nombre("viaje a japon")
            .descripcion_breve("un viaje unico")
            .destino_servicio("japon")
            .fecha_servicio(LocalDate.of(2026, 2, 10))
            .costo_servicio(800.0)
            .build();

    Venta venta = Venta.builder()
            .fecha_venta(LocalDate.of(2025,12,20))
            .medio_pago("tarjeta")
            .cliente(cli)
            .empleado(empleado)
            .producto_turistico(prod)
            .build();

    @Test
    public void deberiaGuardarUnaVenta(){
        clienteRepository.save(cli);
        empleadoRepository.save(empleado);
        productoRepository.save(prod);

        Venta guardada = ventaRepository.save(venta);
        Assertions.assertNotNull(guardada.getNum_venta());

    }

    @Test
    public void deberiaEncontrarLaVentaBuscada(){

        clienteRepository.save(cli);
        empleadoRepository.save(empleado);
        productoRepository.save(prod);

        ventaRepository.save(venta);

        Venta buscada = ventaRepository.findById(1L).orElse(null);
        assert buscada != null;
        Assertions.assertNotNull( buscada.getNum_venta());
    }

    @Test
    public void deberiaTraerTodasLasVentas(){
        clienteRepository.save(cli);
        empleadoRepository.save(empleado);
        productoRepository.save(prod);

        Cliente cli2 = Cliente.builder()
                .build();
        Empleado empleado2 = Empleado.builder()

                .build();
        ProductoTuristico prod2 = ServicioTuristico.builder()
                .nombre("viaje a peru")
                .descripcion_breve("un viaje unico")
                .destino_servicio("PERU")
                .fecha_servicio(LocalDate.of(2026, 2, 22))
                .costo_servicio(850.0)
                .build();
        clienteRepository.save(cli2);
        empleadoRepository.save(empleado2);
        productoRepository.save(prod2);

        Venta venta2 = Venta.builder()
                .cliente(cli2)
                .empleado(empleado2)
                .producto_turistico(prod2)
                .build();
        ventaRepository.save(venta);
        ventaRepository.save(venta2);

        List<Venta> ventas = ventaRepository.findAll();
        Assertions.assertEquals(2, ventas.size());
    }

}
