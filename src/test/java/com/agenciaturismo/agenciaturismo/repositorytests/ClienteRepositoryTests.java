package com.agenciaturismo.agenciaturismo.repositorytests;


import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryTests {

    @Autowired
    private ClienteRepository clienteRepository;


    Cliente cli = Cliente.builder()
            .id_cliente(1L)
            .nombre("carlos")
            .apellido("gomez")
            .dni("1122223333")
            .celular("1155332211")
            .email("asd@gmail.com")
            .nacionalidad("peruano")
            .build();
    @Test
    public void deberiaGuardarUnaVenta(){
        Cliente guardado = clienteRepository.save(cli);

        Assertions.assertNotNull(guardado.getNacionalidad());
        Assertions.assertEquals("peruano", guardado.getNacionalidad());

    }
}
