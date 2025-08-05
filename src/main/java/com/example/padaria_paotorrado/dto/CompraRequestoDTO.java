package com.example.padaria_paotorrado.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompraRequestoDTO {
    private Long usuarioId;
    private List<Long> produtosIds;
}
