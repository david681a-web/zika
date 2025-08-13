package com.example.padaria_paotorrado.Business;

import com.example.padaria_paotorrado.infrastructure.entitys.Padaria;
import com.example.padaria_paotorrado.infrastructure.repository.PadariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PadariaService {

    private final PadariaRepository repositorio;

    public PadariaService(PadariaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void salvarProduto(Padaria padaria) {
        repositorio.save(padaria);
    }

    public Padaria buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void deletarProduto(Long id) {
        Padaria existente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        repositorio.deleteById(id);
    }

    public void atualizarProduto(Long id, Padaria padaria) {
        Padaria existente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Padaria atualizado = Padaria.builder()
                .id(existente.getId())
                .nome(padaria.getNome() != null ? padaria.getNome() : existente.getNome())
                .preco(padaria.getPreco() != null ? padaria.getPreco() : existente.getPreco())
                .build();

        repositorio.save(atualizado);
    }
    public List<Padaria> listarTodos() {
        return repositorio.findAll();
    }
}
