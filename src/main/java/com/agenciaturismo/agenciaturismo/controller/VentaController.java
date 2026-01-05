package com.agenciaturismo.agenciaturismo.controller;

import com.agenciaturismo.agenciaturismo.dto.VentaDTO;
import com.agenciaturismo.agenciaturismo.model.Venta;
import com.agenciaturismo.agenciaturismo.service.VentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaServiceImpl ventaService;

    @PostMapping("/guardar")
    public ResponseEntity<Venta> guardarVenta(@RequestBody VentaDTO ventaDTO) {
        Venta guardada = ventaService.guardarVenta(ventaDTO);
        return new ResponseEntity<>(guardada, HttpStatus.CREATED);
    }

    @GetMapping("/{codigo_producto}")
    public ResponseEntity<Venta> buscarVenta(@PathVariable Long codigo_producto) {
        Venta buscada = ventaService.buscarVenta(codigo_producto);
        return new ResponseEntity<>(buscada, HttpStatus.FOUND);
    }
}
