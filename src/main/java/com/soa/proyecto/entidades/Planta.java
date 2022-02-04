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
@Table(name = "planta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planta implements Serializable {
    @Id
    @Column(name = "codplanta", length = 10)
    private String codPlanta;

    @Column(name = "nomplanta", length = 20)
    private String nomPlanta;

    @Column(name = "direccion", length = 20)
    private String direccion;

    @Column(name = "telefono", length = 10)
    private String telefono;
}
