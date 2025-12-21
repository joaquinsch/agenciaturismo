package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.model.ProductoTuristico;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProductoTuristicoTests {

    ProductoTuristico productoServicio = ServicioTuristico.builder()
            .costo_servicio(100.0)
            .build();
    @Test
    public void deberiaCrearseConCodigoProducto(){
        productoServicio.setCodigo_producto(1L);
        Assertions.assertEquals(1L, productoServicio.getCodigo_producto());
    }

    @Test
    public void deberiaCrearseConTipoProducto(){
        productoServicio.setTipo_producto("SERVICIO");
        Assertions.assertEquals("SERVICIO", productoServicio.getTipo_producto());
    }

    @Test
    public void deberiaSerUnServicio(){
        ProductoTuristico servicioTuristico = ServicioTuristico.builder()
        .codigo_producto(1L)
                .nombre("un servicio")
                .descripcion_breve("el mejor")
                .destino_servicio("cancun")
                .fecha_servicio(LocalDate.of(2026,1,6))
                .costo_servicio(500.0)
                .build();
        Assertions.assertInstanceOf(ServicioTuristico.class, servicioTuristico);
    }

    @Test
    public void deberiaSerUnPaquete(){
        ProductoTuristico paqueteTuristico = PaqueteTuristico.builder()
                .lista_servicios_incluidos(new ArrayList<>())
                .costo_paquete(500.0)
                .build();
        Assertions.assertInstanceOf(PaqueteTuristico.class, paqueteTuristico);
    }


}
