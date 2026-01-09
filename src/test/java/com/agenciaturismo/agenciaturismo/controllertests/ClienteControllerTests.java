package com.agenciaturismo.agenciaturismo.controllertests;


import com.agenciaturismo.agenciaturismo.controller.ClienteController;
import com.agenciaturismo.agenciaturismo.exceptions.ClienteInexistenteError;
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

    @Test
    public void deberiaEncontrarElClienteBuscado() throws Exception{
        Mockito.when(this.clienteService.buscarCliente(1L))
                .thenReturn(cliente);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente))
                ).andExpect(status().isFound())
                .andExpect(jsonPath("$.id_cliente").value(1L));
    }

    @Test
    public void deberiaDarErrorClienteInexistente() throws Exception {
        Mockito.when(clienteService.buscarCliente(2L))
                .thenThrow(new ClienteInexistenteError("El cliente no existe"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente))
                ).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("El cliente no existe"));
    }

    @Test
    public void deberiaEliminarElClienteBuscado() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/clientes/eliminar/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    public void deberiaDarErrorClienteInexistenteAlEliminar() throws Exception {
        // esto se hace cuando queres comprobar que un metodo void tira error
        Mockito.doThrow(new ClienteInexistenteError("El cliente no existe"))
                .when(clienteService)
                .eliminarCliente(2L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/clientes/eliminar/2")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("El cliente no existe"));
    }

    @Test
    public void deberiaEditarElCliente() throws Exception {
        Cliente clienteAEditar = Cliente.builder() //datos q se pasan por parametro
                .id_cliente(1L)
                .nombre("PEDRO")
                .apellido("GONZALEZ")
                .dni("1122223333")
                .celular("1155332211")
                .email("asd@gmail.com")
                .nacionalidad("peruano")
                .build();
        Cliente clienteEditadoDevueltoEsperado = Cliente.builder()
                .id_cliente(1L)
                .nombre("PEDRO")
                .apellido("GONZALEZ")
                .dni("1122223333")
                .celular("1155332211")
                .email("asd@gmail.com")
                .nacionalidad("peruano")
                .build();
        Mockito.when(clienteService.editarCliente(Mockito.any(Cliente.class)))
                .thenReturn(clienteEditadoDevueltoEsperado);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/clientes/editar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteAEditar))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre")
                        .value("PEDRO"))
                .andExpect(jsonPath("$.apellido")
                        .value("GONZALEZ"));
    }
}
