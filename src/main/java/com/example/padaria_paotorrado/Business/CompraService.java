package com.example.padaria_paotorrado.Business;

import com.example.padaria_paotorrado.dto.CompraRequestoDTO;
import com.example.padaria_paotorrado.infrastructure.entitys.Compra;
import com.example.padaria_paotorrado.infrastructure.entitys.Padaria;
import com.example.padaria_paotorrado.infrastructure.entitys.Usuario;
import com.example.padaria_paotorrado.infrastructure.repository.CompraRepository;
import com.example.padaria_paotorrado.infrastructure.repository.PadariaRepository;
import com.example.padaria_paotorrado.infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {
    private final CompraRepository compraRepository;
    private final UsuarioRepository usuarioRepository;
    private final PadariaRepository padariaRepository;


    private double calcularValorTotal(List<Padaria> produtos) {
        return produtos.stream()
                .mapToDouble(Padaria::getPreco)
                .sum();
    }

    public Compra criaCompraComDTO(CompraRequestoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        List<Padaria> produtos = dto.getProdutosIds().stream()
                .map(id -> padariaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id)))
                .toList();

        Compra compra = Compra.builder()
                .usuario(usuario)
                .produtos(produtos)
                .dataCompra(LocalDateTime.now())
                .valorTotal(calcularValorTotal(produtos))
                .build();
        return compraRepository.save(compra);
    }


}
