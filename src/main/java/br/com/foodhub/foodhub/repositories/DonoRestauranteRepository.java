package br.com.foodhub.foodhub.repositories;

import br.com.foodhub.foodhub.entities.Cliente;
import br.com.foodhub.foodhub.entities.DonoRestaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonoRestauranteRepository extends JpaRepository<DonoRestaurante, Long> {

    List<DonoRestaurante> findByNomeContainingIgnoreCase(String nome);
}
