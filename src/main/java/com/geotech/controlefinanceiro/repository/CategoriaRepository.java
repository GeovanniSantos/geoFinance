package com.geotech.controlefinanceiro.repository;

import com.geotech.controlefinanceiro.controller.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
