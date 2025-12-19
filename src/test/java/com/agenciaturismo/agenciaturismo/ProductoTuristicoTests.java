package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.ProductoTuristico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductoTuristicoTests {

    @Test
    public void deberiaCrearseConCodigoProducto(){
        ProductoTuristico producto = new ProductoTuristico();
        producto.setCodigo_producto(1L);
        Assertions.assertEquals(1L, producto.getCodigo_producto());
    }
}
