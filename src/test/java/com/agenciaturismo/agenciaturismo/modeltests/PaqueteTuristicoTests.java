package com.agenciaturismo.agenciaturismo.modeltests;


import com.agenciaturismo.agenciaturismo.exceptions.CostoInvalidoError;
import com.agenciaturismo.agenciaturismo.exceptions.PaqueteInvalidoError;
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
    public void noDeberiaPoderCrearseSinServicios(){
        PaqueteInvalidoError excepcion = Assertions.assertThrows(PaqueteInvalidoError.class,
                () -> PaqueteTuristico.builder()
                        .build());
        Assertions.assertEquals("El paquete no tiene servicios asociados", excepcion.getMessage());
    }

    @Test
    public void noDeberiaPoderCrearseConUnSoloServicio(){
        PaqueteInvalidoError excepcion = Assertions.assertThrows(
                PaqueteInvalidoError.class, ()->
                    PaqueteTuristico.builder()
                    .lista_servicios_incluidos(
                            List.of(ServicioTuristico.builder()
                                    .costo_servicio(100.0)
                                    .build())
                    )
                    .build());
        Assertions.assertEquals("El paquete debe tener mas de un servicio asociado", excepcion.getMessage());

    }

    @Test
    public void noDeberiaPoderCrearseConCostoInvalido(){
        CostoInvalidoError excepcion = Assertions.assertThrows(CostoInvalidoError.class,
                ()-> PaqueteTuristico.builder()
                        .lista_servicios_incluidos(
                                List.of(ServicioTuristico.builder()
                                                .costo_servicio(100.0)
                                                .build(),
                                        ServicioTuristico.builder()
                                                .costo_servicio(100.0)
                                                .build())
                        )
                        .costo_paquete(359.0)
                        .build()
        );
        Assertions.assertEquals("El costo del paquete no coincide con la suma de los servicios menos 10%",
                excepcion.getMessage());
    }


    @Test
    public void deberiaCrearseSiElCostoEsLaSumaDeLosServiciosMenosDiezPorciento(){
        Assertions.assertEquals(180.0, paquete.getCosto_paquete());
    }
}
