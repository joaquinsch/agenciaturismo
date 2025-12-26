package com.agenciaturismo.agenciaturismo.controllertests;

import com.agenciaturismo.agenciaturismo.controller.ServicioTuristicoController;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.service.ServicioTuristicoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServicioTuristicoController.class)
public class ServicioTuristicoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioTuristicoServiceImpl servicioTuristicoService;

    /*@BeforeEach
    public void setUp() {
        // necesario para usar LocalDate
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }*/

    ServicioTuristico servicio = ServicioTuristico.builder()
                        .codigo_producto(1L)
                        .nombre("pasaje")
                        .descripcion_breve("pasaje por colectivo")
                        .destino_servicio("formosa")
                        .fecha_servicio(LocalDate.of(2026, 1, 7))
            .costo_servicio(500.0)
                        .build();

    @Test
    public void deberiaDevolverUnServicioTuristico() throws Exception{
        Mockito.when(servicioTuristicoService.buscarServicio(1L))
                .thenReturn(servicio);



        mockMvc.perform(MockMvcRequestBuilders.get("/api/servicios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("pasaje"))
                .andExpect(jsonPath("$.descripcion_breve").value("pasaje por colectivo"))
                .andExpect(jsonPath("$.destino_servicio").value("formosa"))
                .andExpect(jsonPath("$.fecha_servicio").value("2026-01-07"))
                .andExpect(jsonPath("$.costo_servicio").value(500.0));

    }
}
