package com.example.estoque_confeitaria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @Email(message = "O e-mail deve ser válido")
    @NotBlank(message = "O e-mail é obrigatório")
    @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres")
    private String email;

    @Positive(message = "A idade deve ser um número positivo")
    private Integer idade;

    public Cliente() {}

    public Cliente(String nome, String email, Integer idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }
}
