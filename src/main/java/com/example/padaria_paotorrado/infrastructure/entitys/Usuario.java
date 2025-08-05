package com.example.padaria_paotorrado.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_usuario")
@Entity
public class Usuario {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
@Column(name = "nome")
    private String nome;
@Column(name = "cpf", unique = true)
    private String cpf;
@Column(name = "dataNascimento")
    private String dataNascimento;
@Column(name = "email")
    private String email;
@Column(name = "telefone")
    private String telefone;
}
