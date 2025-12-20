package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioTests {

    @Test
    public void unClienteDeberiaCrearseConDatosDeUsuario(){
        Cliente cliente = Cliente.builder()
                .id_cliente(1L)
                .nombre("carlitos")
                .apellido("perez")
                .direccion("asd 123")
                .dni("12341234")
                .fecha_nac(LocalDate.of(1990,1,26))
                .nacionalidad("argentino")
                .celular("12341234")
                .email("asd@gmail.com")
                .build();
        Assertions.assertEquals("argentino", cliente.getNacionalidad());
    }

    @Test
    public void unEmpleadoDeberiaCrearseConDatosDelUsuario(){
        Empleado empleado = Empleado.builder()
                .id_empleado(1L)
                .cargo("administrativo")
                .sueldo(1000.0)
                .nombre("carlitos")
                .apellido("perez")
                .direccion("asd 123")
                .dni("12341234")
                .fecha_nac(LocalDate.of(1990,1,26))
                .nacionalidad("argentino")
                .celular("12341234")
                .email("asd@gmail.com")
                .build();
        Assertions.assertEquals(1L, empleado.getId_empleado());
    }
}