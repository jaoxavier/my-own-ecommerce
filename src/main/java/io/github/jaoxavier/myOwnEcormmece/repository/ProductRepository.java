package io.github.jaoxavier.myOwnEcormmece.repository;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
