package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServicioTuristicoTests {

    @Test
    public void deberiaCrearseConUnCodigo(){
        ServicioTuristico serv = new ServicioTuristico();
        serv.setCodigo_servicio(1L);
        Assertions.assertEquals(1, serv.getCodigo_servicio());
    }
}
