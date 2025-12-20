package com.agenciaturismo.agenciaturismo.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Cliente extends Usuario {

    private final Long id_cliente;
}