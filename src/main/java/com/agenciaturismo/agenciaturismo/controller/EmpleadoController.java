package com.agenciaturismo.agenciaturismo.controller;


import com.agenciaturismo.agenciaturismo.dto.EmpleadoDTO;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<Empleado> guardarEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        Empleado guardado = empleadoService.guardarEmpleado(empleadoDTO);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @GetMapping("/{id_empleado}")
    public ResponseEntity<Empleado> buscarEmpleado(@PathVariable Long id_empleado) {
        Empleado buscado = empleadoService.buscarEmpleado(id_empleado);
        return new ResponseEntity<>(buscado, HttpStatus.OK);
    }

    @DeleteMapping("/{id_empleado}")
    public ResponseEntity<Empleado> eliminarEmpleado(@PathVariable Long id_empleado) {
        empleadoService.eliminarEmpleado(id_empleado);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Empleado> editarEmpleado(@Valid @RequestBody Empleado empleado) {
        Empleado editado = empleadoService.editarEmpleado(empleado);
        return new ResponseEntity<>(editado, HttpStatus.OK);
    }
}
