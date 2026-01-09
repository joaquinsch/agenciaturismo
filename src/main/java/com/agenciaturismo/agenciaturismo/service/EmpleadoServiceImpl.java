package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.dto.EmpleadoDTO;
import com.agenciaturismo.agenciaturismo.exceptions.EmpleadoInexistenteError;
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

    @Override
    public Empleado buscarEmpleado(Long id_empleado) {
        return this.empleadoRepository.findById(id_empleado)
                .orElseThrow(() -> new EmpleadoInexistenteError("El empleado no existe"));
    }

    @Override
    public void eliminarEmpleado(Long id_cliente) {
        buscarEmpleado(id_cliente);
        empleadoRepository.deleteById(id_cliente);
    }

    @Override
    public Empleado editarEmpleado(Empleado empleado) {
        buscarEmpleado(empleado.getId_empleado());
        Empleado editado = Empleado.builder()
                .id_empleado(1L)
                .nombre(empleado.getNombre())
                .apellido(empleado.getApellido())
                .direccion(empleado.getDireccion())
                .dni(empleado.getDni())
                .fecha_nac(empleado.getFecha_nac())
                .nacionalidad(empleado.getNacionalidad())
                .celular(empleado.getCelular())
                .email(empleado.getEmail())
                .cargo(empleado.getCargo())
                .sueldo(empleado.getSueldo())
                .build();
        return empleadoRepository.save(editado);
    }
}
