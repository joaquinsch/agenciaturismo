package com.agenciaturismo.agenciaturismo.controller;

import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.service.PaqueteTuristicoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteTuristicoController {
    @Autowired
    private PaqueteTuristicoServiceImpl paqueteTuristicoService;

    @PostMapping("/guardar")
    public ResponseEntity<PaqueteTuristico> guardarPaquete(@RequestBody PaqueteTuristico paqueteTuristico) {
        PaqueteTuristico guardado = paqueteTuristicoService.guardarPaquete(paqueteTuristico);
        return new ResponseEntity<>(paqueteTuristico, HttpStatus.CREATED);
    }
}
