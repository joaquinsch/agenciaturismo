package com.agenciaturismo.agenciaturismo.servicetests;

import com.agenciaturismo.agenciaturismo.dto.EmpleadoDTO;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.repository.EmpleadoRepository;
import com.agenciaturismo.agenciaturismo.service.EmpleadoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EmpleadoServiceTests {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

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
            .ventas(new ArrayList<>())
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
    public void deberiaGuardarEmpleado() {
        Mockito.when(empleadoRepository.save(Mockito.any(Empleado.class)))
                .thenReturn(empleado);
        Empleado guardado = empleadoService.guardarEmpleado(empleadoDTO);
        Assertions.assertEquals(0, guardado.getVentas().size());
        Mockito.verify(empleadoRepository).save(Mockito.any(Empleado.class));
    }

    @Test
    public void deberiaEncontrarElEmpleado() {
        Mockito.when(empleadoRepository.findById(1L))
                .thenReturn(Optional.ofNullable(empleado));
        Empleado buscado = empleadoService.buscarEmpleado(1L);
        Assertions.assertEquals(1L, buscado.getId_empleado());
        Assertions.assertEquals("1112341234", buscado.getCelular());
    }

    @Test
    public void deberiaEliminarElEmpleado(){
        Mockito.when(empleadoRepository.findById(1L))
                .thenReturn(Optional.ofNullable(empleado));
        empleadoService.eliminarEmpleado(empleado.getId_empleado());

        Mockito.verify(empleadoRepository).deleteById(1L);
    }

    @Test
    public void deberiaEditarElEmpleado() {
        Mockito.when(empleadoRepository.findById(empleado.getId_empleado()))
                .thenReturn(Optional.ofNullable(empleado));
        Empleado empleadoAEditar = Empleado.builder() //datos q se pasan por parametro
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
        Mockito.when(empleadoRepository.save(Mockito.any(Empleado.class)))
                .thenReturn(empleadoEditadoDevueltoEsperado);
        Empleado editado = empleadoService.editarEmpleado(empleadoAEditar);
        Assertions.assertEquals("vendedor de paquetes", editado.getCargo());
        Assertions.assertEquals("BOLIVIANO", editado.getNacionalidad());
        Assertions.assertEquals("calle falsa 125", editado.getDireccion());
    }


}
