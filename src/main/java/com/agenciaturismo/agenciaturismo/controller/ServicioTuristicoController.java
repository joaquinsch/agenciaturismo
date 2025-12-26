package com.agenciaturismo.agenciaturismo.controller;

import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.service.ServicioTuristicoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servicios")
public class ServicioTuristicoController {
    @Autowired
    private ServicioTuristicoServiceImpl servicioTuristicoService;

    @GetMapping("/{codigo_producto}")
    public ResponseEntity<ServicioTuristico> buscarServicio(@PathVariable Long codigo_producto) {
        ServicioTuristico buscado = this.servicioTuristicoService.buscarServicio(codigo_producto);
        return ResponseEntity.ok(buscado);
    }
}
