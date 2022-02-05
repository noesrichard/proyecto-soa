package com.soa.proyecto.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detalle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coddetalle")
    private Integer codDetalle;

    @ManyToOne
    @JoinColumn(name = "articulo")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "pedido")
    private Pedido pedido;

    @Column(name = "cantidad")
    private Integer cantidad;

}
