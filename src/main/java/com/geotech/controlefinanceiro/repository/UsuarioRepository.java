package com.geotech.controlefinanceiro.repository;

import com.geotech.controlefinanceiro.controller.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository {
    Usuario findByEmail(String email);
}
