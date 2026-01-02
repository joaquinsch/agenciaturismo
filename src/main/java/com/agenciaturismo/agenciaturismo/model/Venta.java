package com.agenciaturismo.agenciaturismo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long num_venta;
    private  LocalDate fecha_venta;
    private  String medio_pago;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private  Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private  Empleado empleado;
    @OneToOne
    @JoinColumn(name = "codigo_producto")
    private  ProductoTuristico producto_turistico;

}
