package com.example.padaria_paotorrado.controller;

import com.example.padaria_paotorrado.Business.PadariaService;
import com.example.padaria_paotorrado.infrastructure.entitys.Padaria;
import com.example.padaria_paotorrado.infrastructure.entitys.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/padaria")
@RequiredArgsConstructor
public class PadariaController {

    private final PadariaService padariaService;

    @PostMapping
    public ResponseEntity<Void> salvarProduto(@RequestBody Padaria padaria) {
        padariaService.salvarProduto(padaria);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Padaria> buscarPadaria(@PathVariable Long id) {
        return ResponseEntity.ok(padariaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarPadaria(@PathVariable Long id, @RequestBody Padaria padaria) {
        padariaService.atualizarProduto(id, padaria);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPadaria(@PathVariable Long id) {
        padariaService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<Padaria>> listarProdutos() {
        return ResponseEntity.ok(padariaService.listarTodos());
    }


}
