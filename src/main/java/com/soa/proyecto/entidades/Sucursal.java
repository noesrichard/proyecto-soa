package com.soa.proyecto.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sucursal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal implements Serializable {
    @Id
    @Column(name = "codsucursal", length = 10)
    private String codSucursal;

    @Column(name = "direccion", length = 30)
    private String direccion;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "ciudad", length = 30)
    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
}
