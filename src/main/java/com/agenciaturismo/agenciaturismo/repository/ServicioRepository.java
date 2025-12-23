package com.agenciaturismo.agenciaturismo.repository;

import com.agenciaturismo.agenciaturismo.model.ServicioTuristico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<ServicioTuristico, Long> {
}
