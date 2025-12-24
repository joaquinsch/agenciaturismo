package com.agenciaturismo.agenciaturismo.servicetests;

import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.repository.ServicioRepository;
import com.agenciaturismo.agenciaturismo.service.ServicioTuristicoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ServicioTuristicoServiceTests {

    @Mock
    ServicioRepository servicioRepository;

    @InjectMocks
    ServicioTuristicoServiceImpl servicioTuristicoService;

    @Test
    public void deberiaGuardarElServicio(){
        ServicioTuristico servicio = ServicioTuristico.builder()
                .codigo_producto(1L)
                .nombre("Viaje a China")
                .descripcion_breve("el mejor")
                .fecha_servicio(LocalDate.of(2026,1,10))
                .costo_servicio(100.0)
                .build();
        Mockito.when(servicioRepository.save(Mockito.any(ServicioTuristico.class))).thenReturn(servicio);

        ServicioTuristico guardado = servicioTuristicoService.guardarServicio(servicio);
        Assertions.assertEquals(1L, guardado.getCodigo_producto());
        Assertions.assertNotNull(guardado);
        Mockito.verify(servicioRepository).save(servicio);

    }
}
