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

    @Test
    public void deberiaEditarElServicioTuristico(){
        Mockito.when(this.servicioRepository.findById(servicio.getCodigo_producto()))
                .thenReturn(Optional.of(servicio));
        // voy a editar el servicio 1 con estos datos, el código_producto no se puede editar
        ServicioTuristico aEditar = ServicioTuristico.builder()
                .codigo_producto(1L)
                .nombre("pasaje")
                .descripcion_breve("pasaje por colectivo")
                .destino_servicio("chaco")
                .fecha_servicio(LocalDate.of(2026,2,1))
                .costo_servicio(400.0)
                .build();

        Mockito.when(servicioRepository.save(Mockito.any(ServicioTuristico.class))).thenReturn(aEditar);

        ServicioTuristico servicioEditado = this.servicioTuristicoService.editarServicio(aEditar);

        Assertions.assertEquals(1L, servicioEditado.getCodigo_producto());
        Assertions.assertEquals("pasaje", servicioEditado.getNombre());
        Assertions.assertEquals("pasaje por colectivo", servicioEditado.getDescripcion_breve());
        Assertions.assertEquals("chaco", servicioEditado.getDestino_servicio());
        Assertions.assertEquals(LocalDate.of(2026,2,1), servicioEditado.getFecha_servicio());
        Assertions.assertEquals(400.0, servicioEditado.getCosto_servicio());

        Mockito.verify(servicioRepository, Mockito.times(1)).save(Mockito.any(ServicioTuristico.class));
    }
}
