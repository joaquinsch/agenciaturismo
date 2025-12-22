package com.agenciaturismo.agenciaturismo.modeltests;

import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ServicioTuristicoTests {

    ServicioTuristico serv = ServicioTuristico.builder()
            .costo_servicio(100.0)
            .build();

    @Test
    public void deberiaCrearseConUnCodigo(){
        serv.setCodigo_producto(1L);
        Assertions.assertEquals(1L, serv.getCodigo_producto());
    }

    @Test
    public void deberiaCrearseConNombre(){
        serv.setNombre("Hotel por noche");
        Assertions.assertEquals("Hotel por noche", serv.getNombre());
    }

    @Test
    public void deberiaCrearseConDescripcionBreve(){
        serv.setDescripcion_breve("Por 4 noches en Cancún");
        Assertions.assertEquals("Por 4 noches en Cancún", serv.getDescripcion_breve());
    }

    @Test
    public void deberiaCrearseConFecha_servicio(){
        serv.setFecha_servicio(LocalDate.of(2026, 1, 15));
        Assertions.assertEquals(LocalDate.of(2026, 1, 15), serv.getFecha_servicio());
    }

    @Test
    public void deberiaCrearseConDestino_servicio(){
        serv.setDestino_servicio("Argentina");
        Assertions.assertEquals("Argentina", serv.getDestino_servicio());
    }

    @Test
    public void deberiaCrearseConCosto_servicio(){
        serv.setCosto_servicio(5000.0);
        Assertions.assertEquals(5000, serv.getCosto_servicio());
    }

    @Test
    public void deberiaValidarQueElCostoNoSeaNegativo(){
        serv.setCosto_servicio(-500.0);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> ServicioTuristico.builder()
                        .codigo_producto(2L)
                        .nombre("pasaje")
                        .descripcion_breve("pasaje por colectivo")
                        .destino_servicio("formosa")
                        .fecha_servicio(LocalDate.of(2026, 1, 7))
                        .costo_servicio(-500.0)
                        .build()
                );

        /*
        2L,"pasaje", "pasaje por colectivo", "formosa", LocalDate.of(2026, 1, 7), -500.0
         */
    }
}
