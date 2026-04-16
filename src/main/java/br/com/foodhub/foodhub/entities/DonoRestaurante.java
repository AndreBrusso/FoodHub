package br.com.foodhub.foodhub.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "donos_restaurantes")
public class DonoRestaurante extends Usuario {
}
