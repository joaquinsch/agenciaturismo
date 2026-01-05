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


}
