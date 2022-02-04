package com.soa.proyecto.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {
    @Id
    @Column(name = "codpedido")
    private Long codPedido;

    @Column(name = "fechapedido")
    private String fechaPedido;

    @ManyToOne
    @JoinColumn(name = "sucursal")
    private Sucursal sucursal;

}
