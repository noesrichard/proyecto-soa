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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codpedido")
    private Integer codPedido;

    @Column(name = "fechapedido")
    private String fechaPedido;

    @ManyToOne
    @JoinColumn(name = "sucursal")
    private Sucursal sucursal;

    public boolean validar(){
        return !(sucursal == null || sucursal.getCodSucursal().equals("") || sucursal.getCodSucursal() == null);
    }


}
