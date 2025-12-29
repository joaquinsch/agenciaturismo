package com.agenciaturismo.agenciaturismo.servicetests;

import com.agenciaturismo.agenciaturismo.dto.PaqueteDTO;
import com.agenciaturismo.agenciaturismo.exceptions.CostoInvalidoError;
import com.agenciaturismo.agenciaturismo.exceptions.PaqueteInvalidoError;
import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.model.ProductoTuristico;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.repository.PaqueteRepository;
import com.agenciaturismo.agenciaturismo.repository.ServicioRepository;
import com.agenciaturismo.agenciaturismo.service.PaqueteTuristicoServiceImpl;
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
import java.util.List;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PaqueteTuristicoServiceTests {

    @Mock
    private PaqueteRepository paqueteRepository;

    @Mock
    private ServicioRepository servicioRepository;

    @InjectMocks
    private PaqueteTuristicoServiceImpl paqueteTuristicoService;

    ServicioTuristico servicio1 = ServicioTuristico.builder()
            .nombre("Viaje por colectivo")
            .costo_servicio(100.0)
            .fecha_servicio(LocalDate.of(2026,2,1))
            .descripcion_breve("viaje largo")
            .destino_servicio("salta")
            .build();

    ServicioTuristico servicio2 = ServicioTuristico.builder()
            .nombre("hotel por noche")
            .costo_servicio(50.0)
            .fecha_servicio(LocalDate.of(2026,1,17))
            .descripcion_breve("cinco estrellas")
            .destino_servicio("caribe")
            .build();

    List<ServicioTuristico> lista = new ArrayList<>(List.of(servicio1, servicio2));

    PaqueteTuristico paquete = PaqueteTuristico.builder()
            //.codigo_producto(1L)
            .lista_servicios_incluidos(lista)
            .costo_paquete(135.0)
            .tipo_producto(ProductoTuristico.TipoProducto.PAQUETE)
            .build();
    PaqueteDTO paqueteDTO = PaqueteDTO.builder()
            .ids_servicios_incluidos(List.of(1L, 2L))
            .costo_paquete(135.0)
            .build();

    @Test
    public void deberiaGuardarUnPaqueteConDosServicios() {
        Mockito.when(this.paqueteRepository.save(Mockito.any(PaqueteTuristico.class)))
                .thenReturn(paquete);
        Mockito.when(this.servicioRepository.findAllById(Mockito.anyIterable()))
                .thenReturn(List.of(servicio1,servicio2));
        PaqueteTuristico guardado = paqueteTuristicoService.guardarPaquete(paqueteDTO);

        Mockito.verify(paqueteRepository).save(Mockito.any(PaqueteTuristico.class));
        //Assertions.assertEquals(1L, guardado.getCodigo_producto());
        Assertions.assertEquals(2, guardado.getLista_servicios_incluidos().size());
        Assertions.assertEquals(135.0, guardado.getCosto_paquete());

    }

    @Test
    public void noDeberiaPoderGuardarseSinServicios(){
       PaqueteDTO paqDTO = PaqueteDTO.builder()
               .ids_servicios_incluidos(new ArrayList<>())
               .costo_paquete(700.0)
               .build();

        PaqueteInvalidoError excepcion = Assertions.assertThrows(PaqueteInvalidoError.class,
                () -> paqueteTuristicoService.guardarPaquete(paqDTO)
        );
        Assertions.assertEquals("El paquete no tiene servicios asociados", excepcion.getMessage());
    }

    @Test
    public void noDeberiaPoderCrearseConCostoInvalido(){
        PaqueteDTO paqDTO = PaqueteDTO.builder()
                .ids_servicios_incluidos(List.of(1L, 2L))
                .costo_paquete(359.0)
                .build();

        Mockito.when(this.servicioRepository.findAllById(Mockito.anyIterable()))
                .thenReturn(List.of(servicio1, servicio2));

        CostoInvalidoError excepcion = Assertions.assertThrows(CostoInvalidoError.class,
                ()-> paqueteTuristicoService.guardarPaquete(paqDTO)
        );
        Assertions.assertEquals("El costo del paquete no coincide con la suma de los servicios menos 10%",
                excepcion.getMessage());
    }

    @Test
    public void noDeberiaPoderCrearseConUnSoloServicio(){
        PaqueteDTO paqDTO = PaqueteDTO.builder()
                .ids_servicios_incluidos(List.of(1L))
                .costo_paquete(100.0)
                .build();

        Mockito.when(this.servicioRepository.findAllById(Mockito.anyIterable()))
                .thenReturn(List.of(servicio1));
        PaqueteInvalidoError excepcion = Assertions.assertThrows(
                PaqueteInvalidoError.class, ()->
                        paqueteTuristicoService.guardarPaquete(paqDTO)
        );
        Assertions.assertEquals("El paquete debe tener mas de un servicio asociado", excepcion.getMessage());
    }

    @Test
    public void deberiaGuardarseConTipoProducto(){
        PaqueteDTO paqDTO = PaqueteDTO.builder()
                .ids_servicios_incluidos(List.of(1L, 2L))
                .costo_paquete(135.0)
                .build();
        Mockito.when(this.servicioRepository.findAllById(Mockito.anyIterable()))
                .thenReturn(List.of(servicio1, servicio2));
        Mockito.when(this.paqueteRepository.save(Mockito.any(PaqueteTuristico.class)))
                .thenReturn(paquete);
        PaqueteTuristico guardado = paqueteTuristicoService.guardarPaquete(paqDTO);
        Assertions.assertEquals(ProductoTuristico.TipoProducto.PAQUETE, guardado.getTipo_producto());
    }
}
