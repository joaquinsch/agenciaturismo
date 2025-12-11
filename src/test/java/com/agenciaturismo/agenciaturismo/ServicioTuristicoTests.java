package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
