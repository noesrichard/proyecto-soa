package com.soa.proyecto.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "articulo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articulo implements Serializable {
    @Id
    @Column(name = "codarticulo", length = 10)
    private String codArticulo;

    @Column(name = "nomarticulo", length = 20)
    private String nomArticulo;

    @Column(name = "color", length = 20)
    private String color;

    @Column(name = "peso")
    private double peso;

    @Column(name = "capacidad")
    private double capacidad;

}
