package com.agenciaturismo.agenciaturismo.controllertests;


import com.agenciaturismo.agenciaturismo.controller.EmpleadoController;
import com.agenciaturismo.agenciaturismo.dto.EmpleadoDTO;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.model.Venta;
import com.agenciaturismo.agenciaturismo.service.EmpleadoServiceImpl;
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
            .nombre("jose")
            .apellido("gomez")
            .direccion("calle falsa 123")
            .dni("1122223333")
            .fecha_nac(LocalDate.of(1999, 9, 26))
            .nacionalidad("argentino")
            .celular("1112341234")
            .email("asd@gmail.com")
            .cargo("tecnico de soporte")
            .sueldo(900000.0)
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

    EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
            .nombre("jose")
            .apellido("gomez")
            .direccion("calle falsa 123")
            .dni("1122223333")
            .fecha_nac(LocalDate.of(1999, 9, 26))
            .nacionalidad("argentino")
            .celular("1112341234")
            .email("asd@gmail.com")
            .cargo("tecnico de soporte")
            .sueldo(900000.0)
            .build();

    @Test
    public void deberiaGuardarElEmpleado() throws Exception {
        Mockito.when(empleadoService.guardarEmpleado(Mockito.any(EmpleadoDTO.class)))
                .thenReturn(empleado);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/empleados/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleadoDTO))
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id_empleado").value(1L))
                .andExpect(jsonPath("$.nombre").value("jose"));
    }

    @Test
    public void deberiaEncontrarElEmpleado() throws Exception {
        Mockito.when(this.empleadoService.buscarEmpleado(1L))
                .thenReturn(empleado);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/empleados/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado))
                ).andExpect(status().isFound())
                .andExpect(jsonPath("$.id_empleado").value(1L));
    }

    @Test
    public void deberiaEliminarElEmpleado() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/empleados/eliminar/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }


}
