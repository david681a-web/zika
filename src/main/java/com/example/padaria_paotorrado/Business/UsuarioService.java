package com.example.padaria_paotorrado.Business;

import com.example.padaria_paotorrado.infrastructure.entitys.Usuario;
import com.example.padaria_paotorrado.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    public void salvarUsuario(Usuario usuario) {
        repository.saveAndFlush(usuario);
    }
    public Usuario buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(()-> new RuntimeException("CPF nao encontrado"));
    }
    public void deletarPorCpf(String cpf) {
        repository.deleteByCpf(cpf);
    }
    public void atualizarPorCpf(String cpf ,Usuario usuario) {
        Usuario usuarioEntity = repository.findByCpf(cpf)
                .orElseThrow(()-> new RuntimeException("CPF nao encontrado"));
        if (usuario.getNome()!=null){
            usuarioEntity.setNome(usuario.getNome());
        }
        if (usuario.getEmail()!=null){
            usuarioEntity.setEmail(usuario.getEmail());
        }
        if (usuario.getTelefone()!=null){
            usuarioEntity.setTelefone(usuario.getTelefone());
        }
        if (usuario.getDataNascimento()!=null){
            usuarioEntity.setDataNascimento(usuario.getDataNascimento());
        }
        repository.saveAndFlush(usuarioEntity);

    }
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

}
