package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.exceptions.ClienteInexistenteError;
import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarCliente(Long id_cliente) {
        return clienteRepository.findById(id_cliente)
                .orElseThrow(
                        () -> new ClienteInexistenteError("El cliente no existe")
                );
    }

    @Override
    public void eliminarCliente(Long id_cliente) {
        buscarCliente(id_cliente);
        this.clienteRepository.deleteById(id_cliente);
    }

    @Override
    public Cliente editarCliente(Cliente cliente) {
        Cliente buscado = buscarCliente(cliente.getId_cliente());
        Cliente editado = Cliente.builder()
                .id_cliente(cliente.getId_cliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .direccion(cliente.getDireccion())
                .dni(cliente.getDni())
                .fecha_nac(cliente.getFecha_nac())
                .nacionalidad(cliente.getNacionalidad())
                .celular(cliente.getCelular())
                .email(cliente.getEmail())
                .build();
        return clienteRepository.save(editado);
    }
}
