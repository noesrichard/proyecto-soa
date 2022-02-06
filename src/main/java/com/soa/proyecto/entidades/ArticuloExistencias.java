package com.soa.proyecto.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloExistencias {

    private Articulo articulo;

    private Long existencias;
}
