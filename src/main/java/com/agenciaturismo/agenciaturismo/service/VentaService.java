package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.dto.VentaDTO;
import com.agenciaturismo.agenciaturismo.model.Venta;

public interface VentaService {
    Venta guardarVenta(VentaDTO ventaDTO);
    Venta buscarVenta(Long num_venta);
    void eliminarVenta(Long num_venta);
}
