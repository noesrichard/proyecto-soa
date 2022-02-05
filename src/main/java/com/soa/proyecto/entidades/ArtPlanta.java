package com.soa.proyecto.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "artplanta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtPlanta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codartplanta")
    private Integer codArtPlanta;

    public Integer getCodigo(){
        return codArtPlanta;
    }

    @ManyToOne
    @JoinColumn(name = "planta")
    private Planta planta;

    @ManyToOne
    @JoinColumn(name = "articulo")
    private Articulo articulo;

    @Column(name = "existencias")
    private Integer existencias;

    @Column(name = "nivelriesgo")
    private Integer nivelRiesgo;

}
