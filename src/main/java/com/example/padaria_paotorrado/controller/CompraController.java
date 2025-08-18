package com.example.padaria_paotorrado.controller;

import com.example.padaria_paotorrado.Business.CompraService;
import com.example.padaria_paotorrado.dto.CompraRequestoDTO;
import com.example.padaria_paotorrado.infrastructure.entitys.Compra;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
@CrossOrigin(origins = "http://localhost:63342")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<Compra> criarCompra(@RequestBody CompraRequestoDTO dto) {
        Compra novaCompra = compraService.criaCompraComDTO(dto);
        return ResponseEntity.ok(novaCompra);

    }
}
