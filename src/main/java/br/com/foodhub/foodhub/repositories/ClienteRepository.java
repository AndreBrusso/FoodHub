package br.com.foodhub.foodhub.repositories;

import br.com.foodhub.foodhub.entities.Cliente;
import io.swagger.v3.oas.annotations.links.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
