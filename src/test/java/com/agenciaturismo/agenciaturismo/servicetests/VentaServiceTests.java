package com.agenciaturismo.agenciaturismo.servicetests;

import com.agenciaturismo.agenciaturismo.dto.VentaDTO;
import com.agenciaturismo.agenciaturismo.exceptions.ClienteInexistenteError;
import com.agenciaturismo.agenciaturismo.exceptions.EmpleadoInexistenteError;
import com.agenciaturismo.agenciaturismo.model.*;
import com.agenciaturismo.agenciaturismo.repository.ClienteRepository;
import com.agenciaturismo.agenciaturismo.repository.EmpleadoRepository;
import com.agenciaturismo.agenciaturismo.repository.ProductoRepository;
import com.agenciaturismo.agenciaturismo.repository.VentaRepository;
import com.agenciaturismo.agenciaturismo.service.VentaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class VentaServiceTests {

    @Mock
    private VentaRepository ventaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private ProductoRepository productoRepository;

    private VentaServiceImpl ventaService;

    @BeforeEach
    void setUp(){
             ventaService = new VentaServiceImpl(
                     ventaRepository,
                     clienteRepository,
                     empleadoRepository,
                     productoRepository
             );
    }

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
    public void deberiaGuardarUnaVenta(){
        Mockito.when(this.ventaRepository.save(Mockito.any(Venta.class)))
                .thenReturn(venta);
        Mockito.when(this.clienteRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.ofNullable(cliente));
        Mockito.when(this.empleadoRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.ofNullable(empleado));
        Mockito.when(this.productoRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.ofNullable(servicioVendido));

        Venta guardada = ventaService.guardarVenta(ventaDTO);
        Assertions.assertEquals(1L, guardada.getNum_venta());
        Assertions.assertEquals(LocalDate.of(2026, 1, 2), guardada.getFecha_venta());
        Assertions.assertEquals(1L, guardada.getCliente().getId_cliente());
        Assertions.assertEquals(1L, guardada.getEmpleado().getId_empleado());
        Assertions.assertEquals(1L, guardada.getProducto_turistico().getCodigo_producto());
    }

    @Test
    public void deberiaDarErrorSiIntentaGuardarLaVentaConClienteInexistente(){
        ClienteInexistenteError excepcion = Assertions.assertThrows(
                ClienteInexistenteError.class,
                () -> ventaService.guardarVenta(ventaDTO)
        );
        Assertions.assertEquals("El cliente ingresado no existe", excepcion.getMessage());
    }

    @Test
    public void deberiaDarErrorSiIntentaGuardarLaVentaConEmpleadoInexistente(){
        Mockito.when(this.clienteRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.ofNullable(cliente));
        EmpleadoInexistenteError excepcion = Assertions.assertThrows(
                EmpleadoInexistenteError.class,
                () -> ventaService.guardarVenta(ventaDTO)
        );
        Assertions.assertEquals("El empleado ingresado no existe", excepcion.getMessage());
    }
}
