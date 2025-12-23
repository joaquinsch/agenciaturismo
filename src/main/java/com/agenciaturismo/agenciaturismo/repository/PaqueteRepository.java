package com.agenciaturismo.agenciaturismo.repository;


import com.agenciaturismo.agenciaturismo.model.PaqueteTuristico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaqueteRepository extends JpaRepository<PaqueteTuristico, Long> {
}
