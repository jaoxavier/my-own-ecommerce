package io.github.jaoxavier.myOwnEcormmece.repository;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderItems, Integer> {
}
