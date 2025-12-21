package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.model.Venta;
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
    Venta venta = Venta.builder().num_venta(1L)
            .fecha_venta(LocalDate.of(2025,12,20))
            .medio_pago("tarjeta")
            .cliente(cli)
            .empleado(empleado)
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
}
