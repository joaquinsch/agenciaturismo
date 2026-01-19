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
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
// @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) ya lo hace @datajpatest
@ActiveProfiles("test")
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
            //.id_cliente(1L)
            .nombre("carlitos")
            .apellido("perez")
            .direccion("asd 123")
            .dni("12341234")
            .fecha_nac(LocalDate.of(1990,1,26))
            .nacionalidad("argentino")
            .celular("12341234")
            .email("asd@gmail.com")
            .build();
    Empleado empleado = Empleado.builder()
           // .id_empleado(1L)
            .cargo("administrativo")
            .sueldo(1000.0)
            .nombre("carlitos")
            .apellido("perez")
            .direccion("asd 123")
            .dni("12341234")
            .fecha_nac(LocalDate.of(1990,1,26))
            .nacionalidad("argentino")
            .celular("12341234")
            .email("asd@gmail.com")
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

        ventaRepository.findById(1L).ifPresent(buscada -> Assertions.assertNotNull(buscada.getNum_venta()));

    }

    @Test
    public void deberiaTraerTodasLasVentas(){
        clienteRepository.save(cli);
        empleadoRepository.save(empleado);
        productoRepository.save(prod);

        Cliente cli2 = Cliente.builder()
                .nombre("carlitos")
                .apellido("perez")
                .direccion("asd 123")
                .dni("12341234")
                .fecha_nac(LocalDate.of(1990,1,26))
                .nacionalidad("argentino")
                .celular("12341234")
                .email("asd@gmail.com")
                .build();
        Empleado empleado2 = Empleado.builder()
                .cargo("administrativo")
                .sueldo(1000.0)
                .nombre("carlitos")
                .apellido("perez")
                .direccion("asd 123")
                .dni("12341234")
                .fecha_nac(LocalDate.of(1990,1,26))
                .nacionalidad("argentino")
                .celular("12341234")
                .email("asd@gmail.com")
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

    @Test
    public void deberiaEliminarUnaVenta(){
        clienteRepository.save(cli);
        empleadoRepository.save(empleado);
        productoRepository.save(prod);

        Venta guardada = ventaRepository.save(venta);
        Assertions.assertNotNull(guardada.getNum_venta());
        Assertions.assertEquals(1L, guardada.getNum_venta());
        Assertions.assertEquals(1, ventaRepository.findAll().size());
        ventaRepository.deleteById(1L);
        Assertions.assertEquals(0, ventaRepository.findAll().size());
    }

    @Test
    void deberiaGenerarIdAutomaticamenteConIdentity() {
        Cliente cli = Cliente.builder()
                .nombre("carlitos")
                .apellido("perez")
                .direccion("asd 123")
                .dni("12341234")
                .fecha_nac(LocalDate.of(1990,1,26))
                .nacionalidad("argentino")
                .celular("12341234")
                .email("asd@gmail.com")
                .build();

        Assertions.assertNull(cli.getId_cliente());

        Cliente guardado = clienteRepository.save(cli);

        Assertions.assertNotNull(guardado.getId_cliente());
        Assertions.assertTrue(guardado.getId_cliente() > 0);
    }

}
