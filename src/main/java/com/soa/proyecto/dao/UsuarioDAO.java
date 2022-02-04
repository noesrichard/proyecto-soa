package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuario, String> {

    Usuario findByUsername(String username);
}
