package com.agenciaturismo.agenciaturismo.controller;

import com.agenciaturismo.agenciaturismo.dto.VentaDTO;
import com.agenciaturismo.agenciaturismo.model.Venta;
import com.agenciaturismo.agenciaturismo.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/guardar")
    public ResponseEntity<Venta> guardarVenta(@RequestBody VentaDTO ventaDTO) {
        Venta guardada = ventaService.guardarVenta(ventaDTO);
        return new ResponseEntity<>(guardada, HttpStatus.CREATED);
    }

    @GetMapping("/{num_venta}")
    public ResponseEntity<Venta> buscarVenta(@PathVariable Long num_venta) {
        Venta buscada = ventaService.buscarVenta(num_venta);
        return new ResponseEntity<>(buscada, HttpStatus.FOUND);
    }

    @DeleteMapping("/eliminar/{num_venta}")
    public ResponseEntity<Venta> eliminarVenta(@PathVariable Long num_venta) {
        Venta buscada = ventaService.buscarVenta(num_venta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
