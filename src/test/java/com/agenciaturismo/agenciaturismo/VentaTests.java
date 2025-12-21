package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.model.Venta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class VentaTests {
    Cliente cli = Cliente.builder()
            .id_cliente(1L)
            .build();
    Venta venta = new Venta(1L, LocalDate.of(2025,12,20), "Tarjeta", cli);

    @Test
    public void deberiaCrearseConNumeroDeVenta(){
        Assertions.assertEquals(1L, venta.getNum_venta());
    }

    @Test
    public void deberiaPoderObtenerDatosDelCliente(){
        Assertions.assertEquals(1L, venta.getCliente().getId_cliente());
    }
}
