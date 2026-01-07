package com.agenciaturismo.agenciaturismo.servicetests;


import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.repository.ClienteRepository;
import com.agenciaturismo.agenciaturismo.service.ClienteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ClienteServiceTests {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

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
    public void deberiaGuardarElCliente() {
        Mockito.when(this.clienteRepository.save(Mockito.any(Cliente.class)))
                .thenReturn(cli);

        Cliente guardado = clienteService.guardarCliente(cli);
        Mockito.verify(clienteRepository).save(cli);
        Assertions.assertEquals("peruano", guardado.getNacionalidad());
    }

    @Test
    public void deberiaEncontrarElClienteBuscado(){
        Mockito.when(clienteRepository.findById(1L))
                .thenReturn(Optional.ofNullable(cli));
        Cliente buscado = clienteService.buscarCliente(1L);
        Assertions.assertEquals(1L, buscado.getId_cliente());
        Assertions.assertEquals("1155332211", buscado.getCelular());

    }
}
