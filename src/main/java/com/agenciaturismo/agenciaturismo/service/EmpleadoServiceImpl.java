package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.dto.EmpleadoDTO;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado guardarEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado guardado = Empleado.builder()
                .nombre(empleadoDTO.getNombre())
                .apellido(empleadoDTO.getApellido())
                .direccion(empleadoDTO.getDireccion())
                .dni(empleadoDTO.getDni())
                .fecha_nac(empleadoDTO.getFecha_nac())
                .nacionalidad(empleadoDTO.getNacionalidad())
                .celular(empleadoDTO.getCelular())
                .email(empleadoDTO.getEmail())
                .cargo(empleadoDTO.getCargo())
                .sueldo(empleadoDTO.getSueldo())
                .build();

        return empleadoRepository.save(guardado);
    }
}
