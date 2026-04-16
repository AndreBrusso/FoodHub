package br.com.foodhub.foodhub.repositories;

import br.com.foodhub.foodhub.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
