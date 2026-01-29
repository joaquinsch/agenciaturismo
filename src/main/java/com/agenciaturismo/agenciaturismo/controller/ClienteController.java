package com.agenciaturismo.agenciaturismo.controller;

import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@Valid @RequestBody Cliente cliente) {
        Cliente guardado = clienteService.guardarCliente(cliente);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id_cliente) {
        Cliente buscado = clienteService.buscarCliente(id_cliente);
        return new ResponseEntity<>(buscado, HttpStatus.OK);
    }

    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<Cliente> eliminarCliente(@PathVariable Long id_cliente) {
        clienteService.eliminarCliente(id_cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Cliente> editarCliente(@Valid @RequestBody Cliente cliente) {
        Cliente buscado = clienteService.editarCliente(cliente);
        return new ResponseEntity<>(buscado, HttpStatus.OK);
    }

}
