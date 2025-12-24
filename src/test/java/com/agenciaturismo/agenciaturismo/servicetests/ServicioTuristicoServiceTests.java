package com.agenciaturismo.agenciaturismo.servicetests;

import com.agenciaturismo.agenciaturismo.exceptions.ServicioInexistenteError;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ServicioTuristicoServiceTests {

    @Mock
    ServicioRepository servicioRepository;

    @InjectMocks
    ServicioTuristicoServiceImpl servicioTuristicoService;

    ServicioTuristico servicio = ServicioTuristico.builder()
            .codigo_producto(1L)
            .nombre("Viaje a China")
            .descripcion_breve("el mejor")
            .fecha_servicio(LocalDate.of(2026,1,10))
            .costo_servicio(100.0)
            .build();

    @Test
    public void deberiaGuardarElServicio(){
        Mockito.when(servicioRepository.save(Mockito.any(ServicioTuristico.class))).thenReturn(servicio);

        ServicioTuristico guardado = servicioTuristicoService.guardarServicio(servicio);
        Assertions.assertEquals(1L, guardado.getCodigo_producto());
        Assertions.assertNotNull(guardado);
        Mockito.verify(servicioRepository).save(servicio);

    }

    @Test
    public void deberiaEliminarUnServicio(){
        Mockito.when(servicioRepository.findById(servicio.getCodigo_producto()))
                .thenReturn(Optional.of(servicio));
        servicioTuristicoService.eliminarServicio(servicio.getCodigo_producto());

        Mockito.verify(servicioRepository).deleteById(servicio.getCodigo_producto());
        Mockito.verifyNoMoreInteractions(servicioRepository); // qcyo
    }

    @Test
    public void deberiaDarErrorSiIntentaEliminarServicioInexistente(){
        ServicioInexistenteError exception = Assertions.assertThrows(
                ServicioInexistenteError.class,() ->
                servicioTuristicoService.eliminarServicio(2L)
        );
        Assertions.assertEquals("El servicio buscado no existe", exception.getMessage());
    }
}
