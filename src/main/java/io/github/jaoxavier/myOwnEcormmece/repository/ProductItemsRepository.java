package io.github.jaoxavier.myOwnEcormmece.repository;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemsRepository extends JpaRepository<ProductItems, Integer> {
}
