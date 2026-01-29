package com.agenciaturismo.agenciaturismo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@Entity
@Table(name = "productos_turisticos")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductoTuristico {

    public enum TipoProducto {
        SERVICIO,
        PAQUETE
    }
    public enum Estado {
        ELIMINADO,
        ACTIVO
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_producto;
    @Enumerated(EnumType.STRING)
    private TipoProducto tipo_producto;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @JsonIgnore
    @OneToMany(mappedBy = "producto_turistico")
    private List<Venta> ventas;

}
