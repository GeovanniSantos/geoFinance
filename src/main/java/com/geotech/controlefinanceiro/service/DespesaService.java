package com.geotech.controlefinanceiro.service;

import com.geotech.controlefinanceiro.controller.Categoria;
import com.geotech.controlefinanceiro.controller.Despesa;
import com.geotech.controlefinanceiro.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public List<Despesa> listarDespesasPorUsuarioEPeriodo(Long usuarioId, LocalDate inicio, LocalDate fim) {
        return despesaRepository.findByUsuarioIdAndDataBetween(usuarioId, inicio, fim);
    }

    public Despesa salvarDespesa(Despesa despesa) {
        if (despesa.getParcelas() != null && despesa.getParcelas() > 1) {
            for (int i = 0; i < despesa.getParcelas(); i++) {
                Despesa parcela = new Despesa();
                parcela.setDescricao(despesa.getDescricao() + " (Parcela " + (i + 1) + ")");
                parcela.setValor(despesa.getValor() / despesa.getParcelas());
                parcela.setData(despesa.getData().plusMonths(i));
                parcela.setCategoria(despesa.getCategoria());
                parcela.setUsuario(despesa.getUsuario());
                despesaRepository.save(parcela);
            }
            return despesa;
        } else {
            return despesaRepository.save(despesa);
        }
    }

    public Despesa atualizarDespesa(Long id, Despesa despesa) {
        Despesa existente = despesaRepository.findById(id).orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
        existente.setDescricao(despesa.getDescricao());
        existente.setValor(despesa.getValor());
        existente.setData(despesa.getData());
        existente.setCategoria(despesa.getCategoria());
        return despesaRepository.save(existente);
    }

    public void removerDespesa(Long id) {
        despesaRepository.deleteById(id);
    }

    public Despesa buscarPorId(Long id) {
        return despesaRepository.findById(id).orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
    }
}
