package br.com.supera.gamestore.model.repositories;

import br.com.supera.gamestore.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
