package com.agenciaturismo.agenciaturismo.controllertests;


import com.agenciaturismo.agenciaturismo.controller.ClienteController;
import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.service.ClienteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTests {

    @MockBean
    private ClienteServiceImpl clienteService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Cliente cliente = Cliente.builder()
            .id_cliente(1L)
            .nombre("carlos")
            .apellido("gomez")
            .dni("1122223333")
            .celular("1155332211")
            .email("asd@gmail.com")
            .nacionalidad("peruano")
            .build();

    @Test
    public void deberiaGuardarElCliente() throws Exception {
        Mockito.when(clienteService.guardarCliente(Mockito.any(Cliente.class)))
                .thenReturn(cliente);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes/guardar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(cliente))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id_cliente").value(1L))
                .andExpect(jsonPath("$.nombre").value("carlos"))
                .andExpect(jsonPath("$.email").value("asd@gmail.com"));
    }
}
