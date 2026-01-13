package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.model.Cliente;

public interface ClienteService {
    Cliente guardarCliente(Cliente cliente);
    Cliente buscarCliente(Long id_cliente);
    Cliente eliminarCliente(Long id_cliente);
    Cliente editarCliente(Cliente cliente);
}
