package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.dto.EmpleadoDTO;
import com.agenciaturismo.agenciaturismo.exceptions.EdicionInvalidaError;
import com.agenciaturismo.agenciaturismo.exceptions.EmpleadoInexistenteError;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.model.Usuario;
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
                .estado(Usuario.Estado.ACTIVO)
                .build();

        return empleadoRepository.save(guardado);
    }

    @Override
    public Empleado buscarEmpleado(Long id_empleado) {
        return this.empleadoRepository.findById(id_empleado)
                .orElseThrow(() -> new EmpleadoInexistenteError("El empleado no existe"));
    }

    @Override
    public Empleado eliminarEmpleado(Long id_cliente) {
        Empleado buscado = buscarEmpleado(id_cliente);
        buscado.setEstado(Usuario.Estado.ELIMINADO);
        return empleadoRepository.save(buscado);
    }

    @Override
    public Empleado editarEmpleado(Empleado empleado) {
        Empleado buscado = buscarEmpleado(empleado.getId_empleado());
        validarEmpleado(empleado.getId_empleado());
        Empleado editado = Empleado.builder()
                .id_empleado(empleado.getId_empleado())
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
                .estado(Usuario.Estado.ACTIVO)
                .build();
        return empleadoRepository.save(editado);
    }

    private Empleado validarEmpleado(Long id_empleado) {
        Empleado empleado = empleadoRepository.findById(id_empleado)
                .orElseThrow(() -> new EmpleadoInexistenteError("El empleado ingresado no existe"));
        if (empleado.getEstado() == Usuario.Estado.ELIMINADO) {
            throw new EdicionInvalidaError("El empleado elegido fue eliminado");
        }
        return empleado;
    }

}
