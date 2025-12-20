package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioTests {

    @Test
    public void unClienteDeberiaCrearseConDatosDeUsuario(){
        Cliente cliente = new Cliente(1L,"carlos",
                "perez", "asd 123", "12341234", LocalDate.of(1990, 3, 26)
                ,"Argentino", "1112341234", "asd123@asd.com");
        Assertions.assertEquals("Argentino", cliente.getNacionalidad());
    }

    @Test
    public void unEmpleadoDeberiaCrearseConDatosDelUsuario(){
        Empleado empleado = new Empleado(1L,"ADMINISTRATIVO",
                500.0, "jose", "perez","asd 123"
                ,"Argentino",  LocalDate.of(1990, 3, 26),"PERUANO", "1155851234", "asd@gmail.com");
        Assertions.assertEquals(1L, empleado.getId_empleado());
    }
}
