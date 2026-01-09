package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.dto.EmpleadoDTO;
import com.agenciaturismo.agenciaturismo.model.Empleado;

public interface EmpleadoService {
    Empleado guardarEmpleado(EmpleadoDTO empleadoDTO);
    Empleado buscarEmpleado(Long id_empleado);
    void eliminarEmpleado(Long id_cliente);
    Empleado editarEmpleado(Empleado empleado);
}
