package com.agenciaturismo.agenciaturismo.controller;

import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import com.agenciaturismo.agenciaturismo.service.ServicioTuristicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servicios")
public class ServicioTuristicoController {
    @Autowired
    private ServicioTuristicoService servicioTuristicoService;

    @GetMapping("/{codigo_producto}")
    public ResponseEntity<ServicioTuristico> buscarServicio(@PathVariable Long codigo_producto) {
        ServicioTuristico buscado = this.servicioTuristicoService.buscarServicio(codigo_producto);
        return new ResponseEntity<>(buscado, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<ServicioTuristico> guardarServicio(@RequestBody ServicioTuristico servicioTuristico) {
        ServicioTuristico guardado = this.servicioTuristicoService.guardarServicio(servicioTuristico);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<ServicioTuristico> editarServicio(@RequestBody ServicioTuristico servicioTuristico) {
        ServicioTuristico editado = this.servicioTuristicoService.editarServicio(servicioTuristico);
        return new ResponseEntity<>(editado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{codigo_producto}")
    public ResponseEntity<ServicioTuristico> eliminarServicio(@PathVariable Long codigo_producto) {
        this.servicioTuristicoService.eliminarServicio(codigo_producto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
