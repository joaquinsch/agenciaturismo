package com.agenciaturismo.agenciaturismo;


import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

public class PaqueteTuristicoTests {

    PaqueteTuristico paquete = PaqueteTuristico.builder()
            .codigo_producto(1L)
            .lista_servicios_incluidos(new ArrayList<>())
            .costo_paquete(100.0)
            .build();

    @Test
    public void deberiaCrearseConUnCodigo(){
        paquete.setCodigo_producto(1L);
        Assertions.assertEquals(1L, paquete.getCodigo_producto());
    }

    @Test
    public void deberiaCrearseConUnCosto(){

        Assertions.assertEquals(100.0, paquete.getCosto_paquete());
    }

    @Test
    public void deberiaCrearseConListaDeServiciosIncluidos(){
        List<ServicioTuristico> lista_servicios = new ArrayList<>();
        lista_servicios.add(ServicioTuristico.builder()
                .codigo_producto(1L)
                .costo_servicio(100.0)
                .build());
        paquete.setLista_servicios_incluidos(lista_servicios);
        Assertions.assertEquals(1,
                paquete.getLista_servicios_incluidos().size());
    }

    @Test
    public void noDeberiaPoderCrearseConCostoCeroONegativo(){
       //List<ServicioTuristico> lista_servicios = new ArrayList<>();
        //paquete.setLista_servicios_incluidos(lista_servicios);
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> PaqueteTuristico.builder()
                        .costo_paquete(-500.0)
                        .build()
        );
    }
}
