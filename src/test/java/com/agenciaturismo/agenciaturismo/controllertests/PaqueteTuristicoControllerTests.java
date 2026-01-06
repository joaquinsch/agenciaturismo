package com.agenciaturismo.agenciaturismo.controllertests;
import com.agenciaturismo.agenciaturismo.controller.PaqueteTuristicoController;
import com.agenciaturismo.agenciaturismo.dto.PaqueteDTO;
import com.agenciaturismo.agenciaturismo.exceptions.PaqueteInexistenteError;
import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.service.PaqueteTuristicoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaqueteTuristicoController.class)
public class PaqueteTuristicoControllerTests {

    @MockBean
    private PaqueteTuristicoServiceImpl paqueteTuristicoService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    ServicioTuristico servicio1 = ServicioTuristico.builder()
            .codigo_producto(1L)
            .nombre("Viaje por colectivo")
            .costo_servicio(100.0)
            .fecha_servicio(LocalDate.of(2026,2,1))
            .descripcion_breve("viaje largo")
            .destino_servicio("salta")
            .build();

    ServicioTuristico servicio2 = ServicioTuristico.builder()
            .codigo_producto(2L)
            .nombre("hotel por noche")
            .costo_servicio(50.0)
            .fecha_servicio(LocalDate.of(2026,1,17))
            .descripcion_breve("cinco estrellas")
            .destino_servicio("caribe")
            .build();

    List<ServicioTuristico> lista = new ArrayList<>(List.of(servicio1, servicio2));

    PaqueteTuristico paquete = PaqueteTuristico.builder()
            .codigo_producto(1L)
            .lista_servicios_incluidos(lista)
            .costo_paquete(135.0)
            .build();

    @Test
    public void deberiaGuardarUnPaqueteConDosServicios() throws Exception {
        Mockito.when(this.paqueteTuristicoService.guardarPaquete(Mockito.any(PaqueteDTO.class)))
                        .thenReturn(paquete);
        PaqueteDTO paqDTO = PaqueteDTO.builder()
                .lista_servicios_incluidos(List.of(1L,2L))
                .costo_paquete(135.0)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/paquetes/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paqDTO))
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.codigo_producto").value(1L));
    }

    @Test
    public void deberiaEncontrarElPaqueteBuscado() throws Exception{
        Mockito.when(this.paqueteTuristicoService.buscarPaquete(1L))
                .thenReturn(paquete);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/paquetes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paquete))
                ).andExpect(status().isFound())
                .andExpect(jsonPath("$.codigo_producto").value(1L));
    }

    @Test
    public void deberiaDarErrorNoEncontrado() throws Exception{
        Mockito.when(this.paqueteTuristicoService.buscarPaquete(2L))
                .thenThrow(PaqueteInexistenteError.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/paquetes/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paquete))
        ).andExpect(status().isNotFound());
    }

    @Test
    public void deberiaEliminarElPaquete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/paquetes/eliminar/1")
                .contentType(MediaType.APPLICATION_JSON)
                //.content(objectMapper.writeValueAsString(paquete))
        ).andExpect(status().isNoContent());
    }
}
