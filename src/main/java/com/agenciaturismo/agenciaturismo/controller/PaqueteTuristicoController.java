package com.agenciaturismo.agenciaturismo.controller;

import com.agenciaturismo.agenciaturismo.dto.PaqueteDTO;
import com.agenciaturismo.agenciaturismo.dto.PaqueteEdicionDTO;
import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import com.agenciaturismo.agenciaturismo.service.PaqueteTuristicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteTuristicoController {
    @Autowired
    private PaqueteTuristicoService paqueteTuristicoService;

    @PostMapping("/guardar")
    public ResponseEntity<PaqueteTuristico> guardarPaquete(@Valid @RequestBody PaqueteDTO paqueteDTO) {
        PaqueteTuristico guardado = paqueteTuristicoService.guardarPaquete(paqueteDTO);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @GetMapping("/{codigo_producto}")
    public ResponseEntity<PaqueteTuristico> buscarPaquete(@PathVariable Long codigo_producto) {
        PaqueteTuristico buscado = paqueteTuristicoService.buscarPaquete(codigo_producto);
        return new ResponseEntity<>(buscado, HttpStatus.FOUND);
    }

    @DeleteMapping("/eliminar/{codigo_producto}")
    public ResponseEntity<PaqueteTuristico> eliminarPaquete(@PathVariable Long codigo_producto) {
        this.paqueteTuristicoService.eliminarPaquete(codigo_producto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/editar")
    public ResponseEntity<PaqueteTuristico> editarPaquete(@Valid @RequestBody PaqueteEdicionDTO paqueteEdicionDTO) {
        PaqueteTuristico editado = this.paqueteTuristicoService.editarPaquete(paqueteEdicionDTO);
        return new ResponseEntity<>(editado, HttpStatus.OK);
    }
}
