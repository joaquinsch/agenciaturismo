package com.agenciaturismo.agenciaturismo.controllertests;

import com.agenciaturismo.agenciaturismo.controller.VentaController;
import com.agenciaturismo.agenciaturismo.dto.VentaDTO;
import com.agenciaturismo.agenciaturismo.dto.VentaEdicionDTO;
import com.agenciaturismo.agenciaturismo.exceptions.ClienteInexistenteError;
import com.agenciaturismo.agenciaturismo.exceptions.EmpleadoInexistenteError;
import com.agenciaturismo.agenciaturismo.exceptions.VentaInexistenteError;
import com.agenciaturismo.agenciaturismo.model.*;
import com.agenciaturismo.agenciaturismo.service.VentaServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

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
    /*************************/
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

    PaqueteTuristico paqueteVendido = PaqueteTuristico.builder()
            .codigo_producto(2L)
            .lista_servicios_incluidos(lista)
            .costo_paquete(135.0)
            .build();
    Venta ventaDePaquete = Venta.builder()
            .num_venta(2L)
            .fecha_venta(LocalDate.of(2026, 2, 1))
            .medio_pago("tarjeta")
            .cliente(cliente)
            .empleado(empleado)
            .producto_turistico(paqueteVendido)
            .build();

    VentaDTO ventaDTOpaquete = VentaDTO.builder()
            .fecha_venta(LocalDate.of(2026, 2, 1))
            .medio_pago("tarjeta")
            .id_cliente(1L)
            .id_empleado(1L)
            .codigo_producto(2L)
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

    @Test
    public void deberiaDevolverUnaVenta() throws Exception{
        Mockito.when(ventaService.buscarVenta(1L))
                .thenReturn(venta);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/ventas/1"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.num_venta").value(1L))
                .andExpect(jsonPath("$.fecha_venta").value("2026-01-02"))
                .andExpect(jsonPath("$.medio_pago").value("tarjeta"))
                .andExpect(jsonPath("$.cliente.nombre").value("carlos"))
                .andExpect(jsonPath("$.producto_turistico.nombre").value("Viaje a China"));
    }

    @Test
    public void deberiaDarErrorVentaInexistente() throws Exception {
        Mockito.when(ventaService.buscarVenta(2L))
                .thenThrow(new VentaInexistenteError("La venta no existe"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/ventas/2")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("La venta no existe"));
    }

    @Test
    public void deberiaGuardarLaVentaDeUnPaqueteTuristico() throws Exception {
        Mockito.when(ventaService.guardarVenta(Mockito.any(VentaDTO.class)))
                .thenReturn(ventaDePaquete);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/ventas/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ventaDTOpaquete))
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.num_venta").value(ventaDePaquete.getNum_venta()))
                .andExpect(jsonPath("$.medio_pago").value(ventaDePaquete.getMedio_pago()))
                .andExpect(jsonPath("$.producto_turistico.codigo_producto")
                        .value(ventaDePaquete.getProducto_turistico().getCodigo_producto()))
                .andExpect(jsonPath("$.producto_turistico.costo_paquete")
                        .value(135.0));


        Mockito.verify(ventaService).guardarVenta(Mockito.any(VentaDTO.class));
    }

    @Test
    public void deberiaEliminarLaVenta() throws Exception {
        Mockito.when(this.ventaService.buscarVenta(1L))
                        .thenReturn(venta);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/ventas/eliminar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNoContent());
    }

    @Test
    @Disabled
    public void deberiaEditarLaVentaDeUnServicioAlCambiarlaPorUnPaquete() throws Exception {
        Venta ventaEditadaDevueltaEsperada = Venta.builder()
                .num_venta(1L)
                .fecha_venta(LocalDate.of(2026, 1, 18))
                .medio_pago("tarjeta de credito")
                .cliente(cliente)
                .empleado(empleado)
                .producto_turistico(paqueteVendido)
                .build();
        Mockito.when(ventaService.editarVenta(Mockito.any(VentaEdicionDTO.class)))
                .thenReturn(ventaEditadaDevueltaEsperada);
        VentaEdicionDTO ventaEdicionDTO = VentaEdicionDTO.builder()
                .num_venta(1L)
                .fecha_venta(LocalDate.of(2026, 1, 18))
                .medio_pago("tarjeta de credito")
                .id_cliente(1L)
                .id_empleado(1L)
                .codigo_producto(2L)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/ventas/editar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ventaEdicionDTO))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.producto_turistico.codigo_producto")
                        .value(2))
                .andExpect(jsonPath("$.producto_turistico.costo_paquete")
                        .value(135.0));
    }
}
