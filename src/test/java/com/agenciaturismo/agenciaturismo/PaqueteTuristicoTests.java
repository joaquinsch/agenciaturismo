package com.agenciaturismo.agenciaturismo;


import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void deberiaCrearseConListaDeServiciosIncluidos(){
        List<ServicioTuristico> lista_servicios = new ArrayList<>();
        lista_servicios.add(new ServicioTuristico());
        paquete.setLista_servicios_incluidos(lista_servicios);
        Assertions.assertEquals(1,
                paquete.getLista_servicios_incluidos().size());
    }

    @Test
    public void noDeberiaPoderCrearseConCeroServicios(){
        List<ServicioTuristico> lista_servicios = new ArrayList<>();
        paquete.setLista_servicios_incluidos(lista_servicios);
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> new PaqueteTuristico(1L, lista_servicios, 500.0)
        );
    }
}
