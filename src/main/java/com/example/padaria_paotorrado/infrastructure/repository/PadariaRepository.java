package com.example.padaria_paotorrado.infrastructure.repository;

import com.example.padaria_paotorrado.infrastructure.entitys.Padaria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PadariaRepository extends JpaRepository<Padaria, Long > {
    Optional<Padaria> findById(Long id);
    @Transactional
    void deleteById(Long id);
}
