package com.agenciaturismo.agenciaturismo.modeltests;


import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class PaqueteTuristicoTests {

    //List<ServicioTuristico> lista = new ArrayList<>();
    PaqueteTuristico paquete = PaqueteTuristico.builder()
            .codigo_producto(1L)
            .lista_servicios_incluidos(
                    List.of(ServicioTuristico.builder()
                                    .costo_servicio(100.0)
                                    .build(),
                            ServicioTuristico.builder()
                                    .costo_servicio(100.0)
                                    .build()))
            .costo_paquete(180.0)
            .build();

    @Test
    public void deberiaCrearseConUnCodigo(){
        paquete.setCodigo_producto(1L);
        Assertions.assertEquals(1L, paquete.getCodigo_producto());
    }

    @Test
    public void deberiaCrearseConUnCosto(){

        Assertions.assertEquals(180.0, paquete.getCosto_paquete());
    }

    @Test
    public void deberiaCrearseConListaDeServiciosIncluidos(){
        Assertions.assertEquals(2,
                paquete.getLista_servicios_incluidos().size());
    }

    @Test
    public void noDeberiaPoderCrearseConCostoCeroONegativo(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> PaqueteTuristico.builder()
                        .costo_paquete(-500.0)
                        .build()
        );
    }

    @Test
    public void deberiaCrearseSiElCostoEsLaSumaDeLosServiciosMenosDiezPorciento(){
        Assertions.assertEquals(180.0, paquete.getCosto_paquete());
    }
}
