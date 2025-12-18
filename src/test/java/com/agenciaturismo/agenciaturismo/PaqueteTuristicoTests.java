package com.agenciaturismo.agenciaturismo;


import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaqueteTuristicoTests {

    PaqueteTuristico paquete = new PaqueteTuristico();

    @Test
    public void deberiaCrearseConUnCodigo(){
        paquete.setCodigo_producto(1L);
        Assertions.assertEquals(1L, paquete.getCodigo_producto());
    }

    @Test
    public void deberiaCrearseConUnCosto(){
        paquete.setCosto_paquete(100.0);
        Assertions.assertEquals(100.0, paquete.getCosto_paquete());
    }
}
