package com.agenciaturismo.agenciaturismo.controller;


import com.agenciaturismo.agenciaturismo.dto.EmpleadoDTO;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/guardar")
    public ResponseEntity<Empleado> guardarEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        Empleado guardado = empleadoService.guardarEmpleado(empleadoDTO);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }
}
