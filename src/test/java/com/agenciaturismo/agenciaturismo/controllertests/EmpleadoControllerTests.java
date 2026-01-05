package com.agenciaturismo.agenciaturismo.controllertests;


import com.agenciaturismo.agenciaturismo.controller.EmpleadoController;
import com.agenciaturismo.agenciaturismo.dto.EmpleadoDTO;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.model.Venta;
import com.agenciaturismo.agenciaturismo.service.EmpleadoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
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

@WebMvcTest(EmpleadoController.class)
public class EmpleadoControllerTests {

    @MockBean
    private EmpleadoServiceImpl empleadoService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Empleado empleado = Empleado.builder()
            .id_empleado(1L)
            .nombre("pedro")
            .apellido("gomez")
            .cargo("vendedor")
            .ventas(null)
            .build();

    Venta venta = Venta.builder()
            .num_venta(1L)
            .fecha_venta(LocalDate.of(2026, 1, 2))
            .medio_pago("tarjeta")
            .cliente(null)
            .empleado(empleado)
            .producto_turistico(null)
            .build();

    @Test
    @Disabled
    public void deberiaGuardarElEmpleado() throws Exception {
        Mockito.when(empleadoService.guardarEmpleado(Mockito.any(EmpleadoDTO.class)))
                .thenReturn(empleado);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/empleados/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null))
                ).andExpect(status().isCreated())
                .andExpect(jsonPath(".$id_empleado").value(1L))
                .andExpect(jsonPath(".$nombre").value("pedro"));
    }


}
