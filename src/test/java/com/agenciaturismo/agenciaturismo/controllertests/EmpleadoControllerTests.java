package com.agenciaturismo.agenciaturismo.controllertests;


import com.agenciaturismo.agenciaturismo.controller.EmpleadoController;
import com.agenciaturismo.agenciaturismo.dto.EmpleadoDTO;
import com.agenciaturismo.agenciaturismo.exceptions.EmpleadoInexistenteError;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.model.Usuario;
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
            .estado(Usuario.Estado.ACTIVO)
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
            .dni("22223333")
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
                .andExpect(jsonPath("$.nombre").value("jose"))
                .andExpect(jsonPath("$.estado").value("ACTIVO"));
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

    @Test
    public void deberiaDarErrorEmpleadoInexistente() throws Exception {
        // esto se hace cuando queres comprobar que un metodo void tira error
        Mockito.doThrow(new EmpleadoInexistenteError("El empleado no existe"))
                .when(empleadoService)
                .eliminarEmpleado(2L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/empleados/eliminar/2")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("El empleado no existe"));
    }

    @Test
    public void deberiaEditarElEmpleado() throws Exception {
        Empleado empleadoAEditar = Empleado.builder() //datos q se pasan por parametro
                .id_empleado(1L)
                .nombre("jose")
                .apellido("gomez")
                .direccion("calle falsa 125")
                .dni("22225555")
                .fecha_nac(LocalDate.of(1999, 9, 26))
                .nacionalidad("BOLIVIANO")
                .celular("+54 11 12341234")
                .email("asd@gmail.com")
                .cargo("vendedor de paquetes")
                .sueldo(900000.0)
                .ventas(null)
                .build();
        Empleado empleadoEditadoDevueltoEsperado = Empleado.builder()
                .id_empleado(1L)
                .nombre("jose")
                .apellido("gomez")
                .direccion("calle falsa 125")
                .dni("1122225555")
                .fecha_nac(LocalDate.of(1999, 9, 26))
                .nacionalidad("BOLIVIANO")
                .celular("1112341234")
                .email("asd@gmail.com")
                .cargo("vendedor de paquetes")
                .sueldo(900000.0)
                .ventas(null)
                .build();
        Mockito.when(empleadoService.editarEmpleado(Mockito.any(Empleado.class)))
                .thenReturn(empleadoEditadoDevueltoEsperado);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/empleados/editar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleadoAEditar))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.cargo")
                        .value("vendedor de paquetes"));
    }
}
