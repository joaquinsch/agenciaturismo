package com.agenciaturismo.agenciaturismo.controllertests;

import com.agenciaturismo.agenciaturismo.controller.ServicioTuristicoController;
import com.agenciaturismo.agenciaturismo.exceptions.FechaInvalidaError;
import com.agenciaturismo.agenciaturismo.exceptions.ServicioInexistenteError;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.service.ServicioTuristicoServiceImpl;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServicioTuristicoController.class)
public class ServicioTuristicoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ServicioTuristicoServiceImpl servicioTuristicoService;


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

    @Test
    public void deberiaDarErrorSiNoEncuentraElServicioBuscado() throws Exception {
        Mockito.when(servicioTuristicoService.buscarServicio(1L))
                .thenThrow(ServicioInexistenteError.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/servicios/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deberiaGuardarElServicioTuristico() throws Exception {
        ServicioTuristico servicio2 = ServicioTuristico.builder()
                .nombre("pasaje")
                .descripcion_breve("pasaje por colectivo")
                .destino_servicio("formosa")
                .fecha_servicio(LocalDate.of(2026, 1, 7))
                .costo_servicio(500.0)
                .build();
        Mockito.when(servicioTuristicoService.guardarServicio(Mockito.any()))
                .thenReturn(servicio2);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/servicios/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicio2))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("pasaje"))
                .andExpect(jsonPath("$.descripcion_breve").value("pasaje por colectivo"))
                .andExpect(jsonPath("$.destino_servicio").value("formosa"))
                .andExpect(jsonPath("$.fecha_servicio").value("2026-01-07"))
                .andExpect(jsonPath("$.costo_servicio").value(500.0));
    }

    @Test
    public void deberiaEditarElServicioTuristico() throws Exception{
        Mockito.when(servicioTuristicoService.editarServicio(Mockito.any()))
                .thenReturn(servicio);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/servicios/editar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(servicio))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo_producto").value(1L))
                .andExpect(jsonPath("$.nombre").value("pasaje"))
                .andExpect(jsonPath("$.descripcion_breve").value("pasaje por colectivo"))
                .andExpect(jsonPath("$.destino_servicio").value("formosa"))
                .andExpect(jsonPath("$.fecha_servicio").value("2026-01-07"))
                .andExpect(jsonPath("$.costo_servicio").value(500.0));
    }

    @Test
    public void deberiaEliminarElServicioTuristico() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/servicios/eliminar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicio))
        ).andExpect(status().isNoContent());
    }

    @Test
    public void deberiaDarErrorSiIntentaGuardarServicioConFechaPasada() throws Exception {
        ServicioTuristico serv = ServicioTuristico.builder()
                .codigo_producto(1L)
                .nombre("pasaje")
                .descripcion_breve("pasaje por colectivo")
                .destino_servicio("formosa")
                .fecha_servicio(LocalDate.of(2026, 1, 1))
                .costo_servicio(500.0)
                .build();

        Mockito.when(this.servicioTuristicoService.guardarServicio(Mockito.any(ServicioTuristico.class)))
                .thenThrow(FechaInvalidaError.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/servicios/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(serv))
        ).andExpect(status().isBadRequest());

    }
}
