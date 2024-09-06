package com.geotech.controlefinanceiro.repository;

import com.geotech.controlefinanceiro.controller.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByUsuarioIdAndDataBetween(Long usuarioId, LocalDate startDate, LocalDate endDate);
}
