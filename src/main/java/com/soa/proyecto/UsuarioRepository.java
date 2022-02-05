package com.soa.proyecto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    public Usuario findByUsername(String username);

}
