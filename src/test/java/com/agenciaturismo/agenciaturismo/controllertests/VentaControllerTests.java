package com.agenciaturismo.agenciaturismo.controllertests;

import com.agenciaturismo.agenciaturismo.controller.VentaController;
import com.agenciaturismo.agenciaturismo.dto.VentaDTO;
import com.agenciaturismo.agenciaturismo.exceptions.ClienteInexistenteError;
import com.agenciaturismo.agenciaturismo.exceptions.EmpleadoInexistenteError;
import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.model.Venta;
import com.agenciaturismo.agenciaturismo.service.VentaServiceImpl;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VentaController.class)
public class VentaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VentaServiceImpl ventaService;

    Cliente cliente = Cliente.builder()
            .id_cliente(1L)
            .nombre("carlos")
            .apellido("perez")
            .direccion("calle falsa 123")
            .dni("12341234")
            .fecha_nac(LocalDate.of(1990, 6, 21))
            .nacionalidad("argentino")
            .celular("1122223333")
            .email("carlosperez@gmail.com")
            .ventas(new ArrayList<>())
            .build();
    Empleado empleado = Empleado.builder()
            .id_empleado(1L)
            .nombre("jose")
            .apellido("rossi")
            .direccion("calle falsa 124")
            .dni("12345555")
            .fecha_nac(LocalDate.of(1967, 5, 24))
            .nacionalidad("argentino")
            .celular("1144445555")
            .email("joserossi@gmail.com")
            .cargo("Vendedor")
            .sueldo(1000.0)
            .ventas(new ArrayList<>())
            .build();

    ServicioTuristico servicioVendido = ServicioTuristico.builder()
            .codigo_producto(1L)
            .nombre("Viaje a China")
            .descripcion_breve("el mejor")
            .fecha_servicio(LocalDate.of(2026,1,10))
            .costo_servicio(100.0)
            .build();

    Venta venta = Venta.builder()
            .num_venta(1L)
            .fecha_venta(LocalDate.of(2026, 1, 2))
            .medio_pago("tarjeta")
            .cliente(cliente)
            .empleado(empleado)
            .producto_turistico(servicioVendido)
            .build();
    VentaDTO ventaDTO = VentaDTO.builder()
            .fecha_venta(LocalDate.of(2026, 1, 2))
            .medio_pago("tarjeta")
            .id_cliente(1L)
            .id_empleado(1L)
            .codigo_producto(1L)
            .build();

    @Test
    public void deberiaGuardarLaVenta() throws Exception {
        Mockito.when(ventaService.guardarVenta(Mockito.any(VentaDTO.class)))
                .thenReturn(venta);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/ventas/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ventaDTO))
        ).andExpect(status().isCreated())
        .andExpect(jsonPath("$.num_venta").value(venta.getNum_venta()))
                .andExpect(jsonPath("$.medio_pago").value(venta.getMedio_pago()))
                .andExpect(jsonPath("$.producto_turistico.codigo_producto")
                        .value(venta.getProducto_turistico().getCodigo_producto()));

        Mockito.verify(ventaService).guardarVenta(Mockito.any(VentaDTO.class));
    }

    @Test
    public void deberiaDarErrorClienteInexistente() throws Exception{
        Mockito.when(this.ventaService.guardarVenta(Mockito.any(VentaDTO.class)))
                .thenThrow(new ClienteInexistenteError("El cliente no existe"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/ventas/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ventaDTO))
        ).andExpect(status().isNotFound())
         .andExpect(jsonPath("$.mensaje").value("El cliente no existe"));
    }

    @Test
    public void deberiaDarErrorEmpleadoInexistente() throws Exception{
        Mockito.when(this.ventaService.guardarVenta(Mockito.any(VentaDTO.class)))
                .thenThrow(new EmpleadoInexistenteError("El empleado no existe"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/ventas/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ventaDTO))
                ).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("El empleado no existe"));
    }
}
