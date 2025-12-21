package com.agenciaturismo.agenciaturismo.repository;

import com.agenciaturismo.agenciaturismo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
