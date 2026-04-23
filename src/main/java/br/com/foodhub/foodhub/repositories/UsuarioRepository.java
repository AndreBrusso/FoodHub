package br.com.foodhub.foodhub.repositories;

import br.com.foodhub.foodhub.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Boolean existsByEmail(String email);

    Optional<Usuario> findByLogin(String login);

}
