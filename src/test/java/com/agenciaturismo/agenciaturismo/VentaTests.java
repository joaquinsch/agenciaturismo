package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class VentaTests {
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
    public void deberiaCrearseConNumeroDeVenta(){
        Assertions.assertEquals(1L, venta.getNum_venta());
    }

    @Test
    public void deberiaPoderObtenerDatosDelCliente(){
        Assertions.assertEquals(1L, venta.getCliente().getId_cliente());
    }

    @Test
    public void deberiaPoderObtenerDatosDelEmpleadoQueHizoLaVenta(){
        Assertions.assertEquals(1L, venta.getEmpleado().getId_empleado());
    }

    @Test
    public void deberiaCrearseConUnServicioTuristico(){
        Assertions.assertEquals(1L, venta.getProducto_turistico().getCodigo_producto());
    }
}
