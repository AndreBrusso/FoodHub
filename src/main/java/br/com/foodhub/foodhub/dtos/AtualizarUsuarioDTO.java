package br.com.foodhub.foodhub.dtos;

import jakarta.validation.constraints.Email;

public record AtualizarUsuarioDTO(

        String nome,

        String login,

        @Email(message = "Email inválido")
        String email,

        String endereco,

        String senha
) {
}
