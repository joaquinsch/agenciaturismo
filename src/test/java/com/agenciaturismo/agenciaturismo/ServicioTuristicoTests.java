package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ServicioTuristicoTests {

    ServicioTuristico serv = new ServicioTuristico();

    @Test
    public void deberiaCrearseConUnCodigo(){
        serv.setCodigo_servicio(1L);
        Assertions.assertEquals(1, serv.getCodigo_servicio());
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
}
