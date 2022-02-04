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
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
    @Id
    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "rol", length = 20)
    private String rol;

}
