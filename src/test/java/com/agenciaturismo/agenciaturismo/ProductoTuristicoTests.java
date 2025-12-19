package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.ProductoTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductoTuristicoTests {

    ProductoTuristico producto = new ProductoTuristico();
    @Test
    public void deberiaCrearseConCodigoProducto(){
        producto.setCodigo_producto(1L);
        Assertions.assertEquals(1L, producto.getCodigo_producto());
    }

    @Test
    public void deberiaCrearseConTipoProducto(){
        producto.setTipo_producto("SERVICIO");
        Assertions.assertEquals("SERVICIO", producto.getTipo_producto());
    }
}
