package com.example.padaria_paotorrado.controller;

import com.example.padaria_paotorrado.Business.PadariaService;
import com.example.padaria_paotorrado.Business.UsuarioService;
import com.example.padaria_paotorrado.infrastructure.entitys.Padaria;
import com.example.padaria_paotorrado.infrastructure.entitys.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario) {
        usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/cpf")
    public ResponseEntity<Usuario> buscarUsuarioPorCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(usuarioService.buscarPorCpf(cpf));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuario(@RequestParam String cpf) {
        usuarioService.deletarPorCpf(cpf);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarUsuario(@RequestParam String cpf, @RequestBody Usuario usuario) {
        usuarioService.atualizarPorCpf(cpf, usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }
}


