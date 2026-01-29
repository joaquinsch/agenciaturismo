package com.agenciaturismo.agenciaturismo.controller;

import com.agenciaturismo.agenciaturismo.dto.VentaDTO;
import com.agenciaturismo.agenciaturismo.dto.VentaEdicionDTO;
import com.agenciaturismo.agenciaturismo.dto.VentaResponseDTO;
import com.agenciaturismo.agenciaturismo.model.Venta;
import com.agenciaturismo.agenciaturismo.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaResponseDTO> guardarVenta(@Valid @RequestBody VentaDTO ventaDTO) {
        VentaResponseDTO guardada = ventaService.guardarVenta(ventaDTO);
        return new ResponseEntity<>(guardada, HttpStatus.CREATED);
    }

    @GetMapping("/{num_venta}")
    public ResponseEntity<VentaResponseDTO> buscarVenta(@PathVariable Long num_venta) {
        VentaResponseDTO buscada = ventaService.buscarVenta(num_venta);
        return new ResponseEntity<>(buscada, HttpStatus.OK);
    }

    @DeleteMapping("/{num_venta}")
    public ResponseEntity<Venta> eliminarVenta(@PathVariable Long num_venta) {
        ventaService.eliminarVenta(num_venta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<VentaResponseDTO> editarVenta(@Valid @RequestBody VentaEdicionDTO ventaEdicionDTO) {
        VentaResponseDTO editada = ventaService.editarVenta(ventaEdicionDTO);
        return new ResponseEntity<>(editada, HttpStatus.OK);
    }
}
