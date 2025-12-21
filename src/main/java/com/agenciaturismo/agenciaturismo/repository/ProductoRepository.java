package com.agenciaturismo.agenciaturismo.repository;

import com.agenciaturismo.agenciaturismo.model.ProductoTuristico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository  extends JpaRepository<ProductoTuristico, Long> {
}
