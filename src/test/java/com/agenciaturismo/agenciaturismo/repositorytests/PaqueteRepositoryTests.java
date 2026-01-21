package com.agenciaturismo.agenciaturismo.repositorytests;

import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.model.ProductoTuristico;
import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.repository.PaqueteRepository;
import com.agenciaturismo.agenciaturismo.repository.ServicioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class PaqueteRepositoryTests {

    @Autowired
    PaqueteRepository paqueteRepository;
    @Autowired
    ServicioRepository servicioRepository;

    @Test
    public void deberiaGuardarPaqueteConServiciosAsociados(){
        ServicioTuristico servicio1 = ServicioTuristico.builder()
                .nombre("VIAJE EN COLECTIVO")
                .destino_servicio("cancun")
                .fecha_servicio(LocalDate.of(2026, 7, 1))
                .descripcion_breve("playas paradisiacas")
                .costo_servicio(100.0)
                .build();
        ServicioTuristico servicio2 = ServicioTuristico.builder()
                .nombre("PASAJE GRATIS")
                .destino_servicio("mexico")
                .fecha_servicio(LocalDate.of(2026, 7, 2))
                .descripcion_breve("mexicooo")
                .costo_servicio(100.0)
                .costo_servicio(100.0)
                .build();
        servicioRepository.save(servicio1);
        servicioRepository.save(servicio2);

        PaqueteTuristico paquete = PaqueteTuristico.builder()
                .lista_servicios_incluidos(List.of(servicio1, servicio2))
                .costo_paquete(180.0)
                .tipo_producto(ProductoTuristico.TipoProducto.PAQUETE)
                .build();
        PaqueteTuristico guardado = paqueteRepository.save(paquete);
        Assertions.assertEquals(2, servicioRepository.findAll().size());
        Assertions.assertNotNull(guardado.getCodigo_producto());
    }
}
