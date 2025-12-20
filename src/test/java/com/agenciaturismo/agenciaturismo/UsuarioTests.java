package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioTests {

    @Test
    public void unClienteDeberiaCrearseConDatosDeUsuario(){
        Cliente cliente = new Cliente("carlos",
                "perez", "asd 123", "12341234", LocalDate.of(1990, 3, 26)
                ,"Argentino", "1112341234", "asd123@asd.com");
        Assertions.assertEquals("Argentino", cliente.getNacionalidad());
    }
}
