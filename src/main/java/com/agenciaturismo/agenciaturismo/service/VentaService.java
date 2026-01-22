package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.dto.VentaDTO;
import com.agenciaturismo.agenciaturismo.dto.VentaEdicionDTO;
import com.agenciaturismo.agenciaturismo.dto.VentaResponseDTO;
import com.agenciaturismo.agenciaturismo.model.Venta;

public interface VentaService {
    VentaResponseDTO guardarVenta(VentaDTO ventaDTO);
    Venta buscarVenta(Long num_venta);
    void eliminarVenta(Long num_venta);
    VentaResponseDTO editarVenta(VentaEdicionDTO ventaEdicionDTO);
}
