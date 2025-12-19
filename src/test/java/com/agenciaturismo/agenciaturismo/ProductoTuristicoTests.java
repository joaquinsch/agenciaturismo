package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.model.ProductoTuristico;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ProductoTuristicoTests {

    ProductoTuristico productoServicio = new ServicioTuristico();
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
        ProductoTuristico servicioTuristico = new ServicioTuristico(1L, "un servicio", "el mejor", "cancun", LocalDate.of(2026,1,6), 500.0);
        Assertions.assertInstanceOf(ServicioTuristico.class, servicioTuristico);
    }

    @Test
    public void deberiaSerUnPaquete(){
        ProductoTuristico paqueteTuristico = new PaqueteTuristico();
        Assertions.assertInstanceOf(PaqueteTuristico.class, paqueteTuristico);
    }


}
