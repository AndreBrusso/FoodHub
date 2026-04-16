package br.com.foodhub.foodhub.dtos;

import br.com.foodhub.foodhub.entities.Usuario;

import java.time.LocalDate;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        String endereco,
        LocalDate dataUltAlteracao
) {
    public static UsuarioResponseDTO from(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getEndereco(),
                usuario.getDataUltAlteracao()
        );
    }
}
