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
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {
    @Id
    @Column(name = "codcliente", length = 10)
    private String codCliente;

    @Column(name = "nomcliente", length = 20)
    private String nomCliente;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "limitecredito")
    private Double limiteCredito;

    @Column(name = "pctdescuento")
    private Integer pctDescuento;

}
